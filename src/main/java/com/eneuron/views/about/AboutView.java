package com.eneuron.views.about;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addon.twitter.TweetButton;

import com.eneuron.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinResponse;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.communication.IndexHtmlRequestListener;
import com.vaadin.flow.server.communication.IndexHtmlResponse;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout implements AfterNavigationObserver{
//	public class AboutView extends VerticalLayout implements AfterNavigationObserver, IndexHtmlRequestListener, VaadinServiceInitListener{

	
	
	
    public AboutView() {
		
	
        setSpacing(false);

        Image img = new Image("images/webnazar-200x200.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        add(new H2("Vision Thoroughly"));
        add(new Paragraph("What's New ON - Trends, Technology, Science, Innovation, News, Education and more..."));
        
        TweetButton tweetbutton = TweetButton.follow("WebNazar")
        .hideScreenName()
        .large()
        .withCount(TweetButton.Count.none);
        
        add(tweetbutton);
        
        
//        Tweet tweet = new Tweet("1515635858422272000")
//        	    .withoutCards()
//        	    .withLightTheme()
//        	    .withoutConversation()
//        	    .withHashtag("WebNazar", "reactive")
//        	    .withRelated("webnazar")
//        	    .withVia("webnazar");
//        
//        tweet.setWidthFull();
//        tweet.setWidth("100%");
//        
//        add(tweet);
        
        add(createSocialLinks());
        
        

        setSizeFull();
        //setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
    
    private Component createSocialLinks() {
    	
    	HorizontalLayout layout = new HorizontalLayout();
    	layout.setMargin(true);
    	layout.setPadding(true);
    	layout.setAlignItems(Alignment.CENTER);
    	//layout.setWidthFull();
    	
    	Span span = new Span("");
    	span.addClassNames("la", "la-twitter");
    	Anchor social = new Anchor("https://twitter.com/webnazar", span);
    	layout.add(social);
    	
    	span = new Span("");
    	span.addClassNames("la", "la-youtube");
    	social = new Anchor("https://www.youtube.com/c/webnazar", span);
    	layout.add(social);
    	
    	span = new Span("");
    	span.addClassNames("la", "la-facebook");
    	social = new Anchor("https://www.facebook.com/webnazar", span);
    	layout.add(social);
    	
    	span = new Span("");
    	span.addClassNames("la", "la-instagram");
    	social = new Anchor("https://www.instagram.com/webnazar/", span);
    	layout.add(social);
    	
    	span = new Span("");
    	span.addClassNames("la", "la-linkedin");
    	social = new Anchor("https://www.linkedin.com/in/webnazar/", span);
    	layout.add(social);
    	
    	span = new Span("");
    	span.addClassNames("la", "la-pinterest");
    	social = new Anchor("https://www.pinterest.com/webnazar", span);
    	layout.add(social);
    	
    	span = new Span("");
    	span.addClassNames("la", "la-reddit");
    	social = new Anchor("https://www.reddit.com/user/webnazar/", span);
    	layout.add(social);
    	
    	span = new Span("");
    	span.addClassNames("la", "la-tumblr");
    	social = new Anchor("https://webnazar.tumblr.com/", span);
    	layout.add(social);
    	
    	span = new Span("");
    	span.addClassNames("la", "la-google");
    	social = new Anchor("https://webnazar.blogspot.com/", span);
    	layout.add(social);
    	
    	span = new Span("");
    	span.addClassNames("la", "la-wordpress");
    	social = new Anchor("https://webnazar.wordpress.com/", span);
    	layout.add(social);
    	    	
    	
    	return layout;
    	
    }

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		
//		UI.getCurrent().getPage().addJavaScript("https://www.googletagmanager.com/gtag/js?id=UA-80976618-1", LoadMode.LAZY);
//		
//		UI.getCurrent().getPage().executeJs("window.dataLayer = window.dataLayer || [];"
//        		+ "function gtag(){dataLayer.push(arguments);}"
//        		+ "gtag('js', new Date());"
//        		+ "gtag('config', 'UA-80976618-1');", "");
		
		//serviceInit(new ServiceInitEvent(VaadinService.getCurrent()));
		
		new VaadinServiceInitListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void serviceInit(ServiceInitEvent event) {
				
				
				System.out.println("Service");
			}
		};
		
	}
	
//	@Override
//    public void serviceInit(ServiceInitEvent event) {
//        
//		System.out.println("Service initiated");
//		
//		event.addIndexHtmlRequestListener(response -> {
//            // IndexHtmlRequestListener to change the bootstrap page
//			System.out.println("Service initiated");
//			modifyIndexHtmlResponse(response);
//
//        });
//
//        event.addDependencyFilter((dependencies, filterContext) -> {
//            // DependencyFilter to add/remove/change dependencies sent to
//            // the client
//            return dependencies;
//        });
//
//        event.addRequestHandler((session, request, response) -> {
//            // RequestHandler to change how responses are handled
//            return false;
//        });
//    }
//
//	@Override
//	public void modifyIndexHtmlResponse(IndexHtmlResponse indexHtmlResponse) {
//		
//		Document document = indexHtmlResponse.getDocument();
//		Element head = document.head();
//		
//		System.out.println(head.toString());
//		
//	}

	

}
