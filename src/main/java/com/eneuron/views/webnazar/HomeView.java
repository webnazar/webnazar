package com.eneuron.views.webnazar;


import java.util.Optional;

import javax.servlet.http.Cookie;

import org.vaadin.addon.twitter.Timeline;
import org.vaadin.addon.twitter.TweetButton;
import org.vaadin.addon.twitter.Timeline.Chrome;

import com.eneuron.views.MainLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.shared.ui.LoadMode;

//@PageTitle("WEB NAZAR - Vision Thoroughly")
@RouteAlias(value = "/:PROFILE?/:action?(view)", layout = MainLayout.class)
@Route(value = "webnazar", layout = MainLayout.class)

@CssImport(value = "./themes/webnazar/home.css", themeFor="vaadin-text-field")
public class HomeView extends VerticalLayout implements BeforeEnterObserver, HasDynamicTitle, AfterNavigationObserver{

	private TextField tfsearch;
	VerticalLayout vLResult;
	String title = "WEBNAZAR - Vision Thoroughly";
	
	private final String PROFILE = "PROFILE";
	private final String PROFILE_ROUTE_TEMPLATE = "/%s";
	
    public HomeView() {
            	
    	setSpacing(false);
    	setMargin(false);
    	setPadding(false);
    	
        Image img = new Image("images/webnazar-150x150.png", "WEBNAZAR");
        img.setWidth("150px");
        add(img);
        
        HorizontalLayout hlLayout = new HorizontalLayout();
        hlLayout.setPadding(true);
        hlLayout.setWidthFull();
        add(hlLayout);
        
        tfsearch = new TextField();
        tfsearch.setPattern("");
        tfsearch.setLabel("Search");
        tfsearch.setAutofocus(true);
        tfsearch.setAutoselect(true);
        tfsearch.setMaxLength(100);
        tfsearch.addClassName("tfsearch");
        tfsearch.setPrefixComponent(VaadinIcon.SEARCH.create());
        tfsearch.setClearButtonVisible(true);
        tfsearch.setPlaceholder("twitter - user, person, brand or group...");
        tfsearch.setWidthFull();

        
        tfsearch.addValueChangeListener(v -> {
        	
        	if(tfsearch.getValue().length()>3) {
        	
        		UI.getCurrent().navigate(String.format(PROFILE_ROUTE_TEMPLATE, tfsearch.getValue().replaceAll("\\s+","").replace("#", "").replace("@", "")));
        		
        	}
        	
        	
        });
        
        
        hlLayout.add(tfsearch);
        
        vLResult = new VerticalLayout();
        vLResult.setSpacing(false);
        vLResult.setPadding(false);
        vLResult.setMargin(false);
        vLResult.setWidthFull();
        
        add(vLResult);
        
        setSizeFull();
        //setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        
        
    }
    
//    @Override
//	public void afterNavigation(AfterNavigationEvent event) {
//		// TODO Auto-generated method stub
//		generateLikes();
//	}
    
    private void generateTweets(String profile) {
    	
    	
    	vLResult.removeAll();
    	
    	Timeline timeline;
    	
    	timeline = Timeline.url("https://twitter.com/"+profile).withHashtag("webnazar").withRelated("webnazar").withLanguage(MainLayout.slanguage);
        	
		timeline.setSizeFull();
    	
    	vLResult.add(timeline);
    	
    	timeline.scrollIntoView();
    	
    }
    
	private void generateProfile() {
    	
		vLResult.removeAll();
    	
		Timeline timeline = Timeline.profile("WebNazar").withHashtag("webnazar").withRelated("webnazar").withLanguage(MainLayout.slanguage);
						
		timeline.setWidthFull();
    	
    	vLResult.add(timeline);
    	
    	timeline.scrollIntoView();
    	
    }
	
	 @Override
	    public void beforeEnter(BeforeEnterEvent event) {
		 
		 
	        Optional<String> profile = event.getRouteParameters().get(PROFILE);
	        
	        if (profile.isPresent()) {
	            
	        	title = "WEBNAZAR - "+profile.get();
	        	getPageTitle();
	        	
	        	generateTweets(profile.get());
	        	
	        }else {
	        	
	        	title = "WEBNAZAR - Vision Thoroughly";
	        	getPageTitle();
	        	
	        	generateProfile();
	        	
	        }
	        
	    }

	@Override
	public String getPageTitle() {
		
		return title;
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		
		UI.getCurrent().getPage().addJavaScript("https://www.googletagmanager.com/gtag/js?id=UA-80976618-1", LoadMode.LAZY);
		
		UI.getCurrent().getPage().executeJs("window.dataLayer = window.dataLayer || [];"
        		+ "function gtag(){dataLayer.push(arguments);}"
        		+ "gtag('js', new Date());"
        		+ "gtag('config', 'UA-80976618-1');", "");
		
		//add(new Html("<div id=\"google_translate_element\">Test</div>"));
		//UI.getCurrent().getPage().addJavaScript("https://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit");
		//UI.getCurrent().getPage().executeJs("function googleTranslateElementInit() { new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');}", "");
		
	}
	
	
}
