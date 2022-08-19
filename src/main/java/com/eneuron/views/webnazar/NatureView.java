package com.eneuron.views.webnazar;


import org.vaadin.addon.twitter.Timeline;

import com.eneuron.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.ui.LoadMode;

@PageTitle("WEB NAZAR - Nature")
@Route(value = "nature", layout = MainLayout.class)
@CssImport(value = "./themes/webnazar/home.css", themeFor="vaadin-text-field")
public class NatureView extends VerticalLayout implements AfterNavigationObserver {

	private TextField tfsearch;
	VerticalLayout vLResult;
	
	
    public NatureView() {
        
    	
    	setSpacing(false);
    	setMargin(false);
    	setPadding(false);
        
        setWidthFull();
        
        vLResult = new VerticalLayout();
        vLResult.setPadding(false);
        vLResult.setMargin(false);
        vLResult.setWidthFull();
        vLResult.setWidthFull();
        
        add(vLResult);
        
        setSizeFull();
        //setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
    
    

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		// TODO Auto-generated method stub
		generateWhatsNewOnWeb();
		
UI.getCurrent().getPage().addJavaScript("https://www.googletagmanager.com/gtag/js?id=UA-80976618-1", LoadMode.LAZY);
		
		UI.getCurrent().getPage().executeJs("window.dataLayer = window.dataLayer || [];"
        		+ "function gtag(){dataLayer.push(arguments);}"
        		+ "gtag('js', new Date());"
        		+ "gtag('config', 'UA-80976618-1');", "");
	}
    
	private void generateWhatsNewOnWeb() {
    	
    	
		Timeline timeline = Timeline.list("1554096176127160320").withHashtag("webnazar").withRelated("webnazar");
		//Timeline timeline = Timeline.url("https://twitter.com/i/topics/1103294729079349250");
		
		
    	timeline.setWidthFull();
    	
    	vLResult.add(timeline);
    	
    	
    	
    }
}
