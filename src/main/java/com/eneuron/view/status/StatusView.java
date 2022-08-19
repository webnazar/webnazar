package com.eneuron.view.status;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vaadin.addon.twitter.Tweet;
import org.vaadin.addon.twitter.TweetButton;

import com.eneuron.util.Constant;
import com.eneuron.views.MainLayout;
import com.twitter.clientlib.ApiClient;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.api.TweetsApi.APIfindTweetByIdRequest;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.AnimatedGif;
import com.twitter.clientlib.model.Get2TweetsIdResponse;
import com.twitter.clientlib.model.Media;
import com.twitter.clientlib.model.Photo;
import com.twitter.clientlib.model.ResourceUnauthorizedProblem;
import com.twitter.clientlib.model.Variant;
import com.twitter.clientlib.model.Video;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.PushConfiguration;
import com.vaadin.flow.component.ReconnectDialogConfiguration;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Inline.Position;
import com.vaadin.flow.component.page.Inline.Wrapping;
import com.vaadin.flow.component.page.LoadingIndicatorConfiguration;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.internal.HtmlUtils;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.communication.IndexHtmlRequestListener;
import com.vaadin.flow.server.communication.IndexHtmlResponse;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.shared.ui.LoadMode;
import com.vaadin.flow.theme.lumo.LumoUtility;

//@PageTitle("WEB NAZAR - Vision Thoroughly")
@Route(value = "status/:STATUS?/:action?(view)", layout = MainLayout.class)

@CssImport(value = "./themes/webnazar/status.css")
public class StatusView extends VerticalLayout implements BeforeEnterObserver, HasDynamicTitle, AfterNavigationObserver{

	
	VerticalLayout vLResult;
	String stitle = "WEBNAZAR - Status";
	
	private final String LANGUAGE = "LANGUAGE";
	private final String STATUS = "STATUS";
	private final String STATUS_ROUTE_TEMPLATE = "status/%s/%s";
	
	TwitterApi apiInstance = null;
	
	String tweetId = "";
	String url = "";
	
	H2 title;
	
    public StatusView() {
            	
    	setSpacing(false);
    	setPadding(false);
    	setMargin(false);
        
        setWidthFull();

        Image img = new Image("images/webnazar-150x150.png", "WEBNAZAR");
        img.setWidth("150px");
        add(img);
        img.addClickListener(v -> {
        	
        	UI.getCurrent().getPage().open("/webnazar", "_self");
        	
        });
        
        HorizontalLayout hlLayout = new HorizontalLayout();
        hlLayout.setWidthFull();
        add(hlLayout);
        
        add(new Html("<span>&nbsp;</span>"));
        
        TweetButton btweet = TweetButton.follow("WebNazar")
			    .hideScreenName()
			    .large()
			    .withCount(TweetButton.Count.none).withLanguage(MainLayout.slanguage);
				
				 add(btweet);
        
        vLResult = new VerticalLayout();
        vLResult.setDefaultHorizontalComponentAlignment(Alignment.AUTO);
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
    
	 @Override
	    public void beforeEnter(BeforeEnterEvent event) {
		 
	        Optional<String> tweet = event.getRouteParameters().get(STATUS);
	        
	        
	        if (tweet.isPresent()) {
	            
	        	tweetId = tweet.get();
	        	
	        	url = Constant.DOMAIN+event.getLocation().getPath();
	        	
	        	apiInstance = new TwitterApi(new TwitterCredentialsBearer(Constant.TWITTER_BEARER_TOKEN));
				
	    		Set<String> expansions = new HashSet<>(Arrays.asList("author_id", "attachments.media_keys")); // Set<String> | A comma separated list of fields to expand.
	    		Set<String> tweetFields = new HashSet<>(Arrays.asList("created_at", "lang", "context_annotations")); // Set<String> | A comma separated list of Tweet fields to display.
	    		Set<String> userFields = new HashSet<>(Arrays.asList("profile_image_url", "name")); // Set<String> | A comma separated list of User fields to display.
	    		Set<String> mediaFields = new HashSet<>(Arrays.asList("preview_image_url", "url", "variants")); // Set<String> | A comma separated list of User fields to display.
	    		
	    		    try {
	    		    	
	    		    	
	    		     // findTweetById
	    		     //SingleTweetLookupResponse result = apiInstance.tweets().findTweetById("1558441156446490624", expansions, tweetFields, userFields, mediaFields, null, null);
	    		    Get2TweetsIdResponse result = apiInstance.tweets().findTweetById(tweetId)
	    		    		.expansions(expansions)
	    		    		.mediaFields(mediaFields)
	    		    		.userFields(userFields)
	    		    		.execute();
	    		    	
	    		     
	    		     if(result.getErrors() != null && result.getErrors().size() > 0) {
	    		       System.out.println("Error:");

	    		       result.getErrors().forEach(e -> {
	    		         System.out.println(e.toString());
	    		         if (e instanceof ResourceUnauthorizedProblem) {
	    		           System.out.println(((ResourceUnauthorizedProblem) e).getTitle() + " " + ((ResourceUnauthorizedProblem) e).getDetail());
	    		         }
	    		       });
	    		     } else {
	    		       //System.out.println("findTweetById - Tweet Text: " +result.toString());
	    		       //.getIncludes().getMedia().get(0).getMediaKey()
	    		    	 
	    		       createTweetCard(result);
	    		       
	    		     }
	    		    } catch (ApiException e) {
	    		      System.err.println("Status code: " + e.getCode());
	    		      System.err.println("Reason: " + e.getResponseBody());
	    		      System.err.println("Response headers: " + e.getResponseHeaders());
	    		      e.printStackTrace();
	    		    }
	    		    
	    		    
	        }
	        
	        
	        
	        
	    }

	@Override
	public String getPageTitle() {
		
		
		return stitle;
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		
		
		  
		
//		if(tweetId.isEmpty()) {
//			
//			add(TweetButton.share("http://www.webnazar.com/"));
//			
//		}else {
//			
//			generateTweets(tweetId);
//			
//		}
		
		UI.getCurrent().getPage().addJavaScript("https://www.googletagmanager.com/gtag/js?id=UA-80976618-1", LoadMode.LAZY);
		
		UI.getCurrent().getPage().executeJs("window.dataLayer = window.dataLayer || [];"
        		+ "function gtag(){dataLayer.push(arguments);}"
        		+ "gtag('js', new Date());"
        		+ "gtag('config', 'UA-80976618-1');", "");
		
		
		
		
		
		
        }

	private void createTweetCard(Get2TweetsIdResponse result) {
		
		vLResult.removeAll();
		
		Label title = new Label(result.getData().getText());
		title.addClassName(LumoUtility.FontSize.LARGE);
		vLResult.add(title);
		
		HorizontalLayout hlayout = new HorizontalLayout();
		hlayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
		vLResult.add(hlayout);
		
		Avatar avatar = new Avatar(result.getIncludes().getUsers().get(0).getName(), result.getIncludes().getUsers().get(0).getProfileImageUrl().toString());
		hlayout.add(avatar);
		
		VerticalLayout vlayout = new VerticalLayout();
		vlayout.setSpacing(false);
		vlayout.setMargin(false);
		vlayout.setPadding(false);
		hlayout.add(vlayout);
		
		title = new Label(result.getIncludes().getUsers().get(0).getName());
		title.addClassName(LumoUtility.FontSize.SMALL);
		vlayout.add(title);
		
		title = new Label("@"+result.getIncludes().getUsers().get(0).getUsername());
		title.addClassName(LumoUtility.FontSize.SMALL);
		vlayout.add(title);
		
		Media media = result.getIncludes().getMedia().get(0);
		
		if (media instanceof Video v) {
			
			List<Variant> list = v.getVariants();
			for(Variant variant: list) {
				
				if(variant.getContentType().equals("video/mp4")) {
					
//					Div div = new Div();
//					div.addClassName("container");
//					vLResult.add(div);
					
					Html video = new Html("<video width=\"100%\" height=\"auto\" class=\"video\" controls>"
							+ "  <source src=\""+variant.getUrl().toString()+"\" type=\"video/mp4\">"
							+ "  <source src=\"movie.ogg\" type=\"video/ogg\">"
							+ "Your browser does not support the video tag."
							+ "</video>");
					
					
					vLResult.add(video);

					break;
				}
				
			}
			
			
			
//			UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"twitter:image\");meta.setAttribute(\"content\", \""+v.getPreviewImageUrl()+"\");document.head.appendChild(meta);");
//			UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"og:image\");meta.setAttribute(\"content\", \""+v.getPreviewImageUrl()+"\");document.head.appendChild(meta);");
//			UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"og:image:url\");meta.setAttribute(\"content\", \""+v.getPreviewImageUrl()+"\");document.head.appendChild(meta);");
	    
	    }
	    if (media instanceof Photo v) {
	    	
			Image image = new Image(v.getUrl() != null ? v.getUrl().toString() : "", "");
			image.setWidthFull();
			vLResult.add(image);
	    	
//			UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"twitter:image\");meta.setAttribute(\"content\", \""+v.getUrl()+"\");document.head.appendChild(meta);");
//			UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"og:image\");meta.setAttribute(\"content\", \""+v.getUrl()+"\");document.head.appendChild(meta);");
//			UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"og:image:url\");meta.setAttribute(\"content\", \""+v.getUrl()+"\");document.head.appendChild(meta);");
		    
	    }
	    if (media instanceof AnimatedGif v) {
	        
	    	Image image = new Image(v.getPreviewImageUrl() != null ? v.getPreviewImageUrl().toString() : "", "");
			image.setWidthFull();
			vLResult.add(image);
	    
			
//			UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"twitter:image\");meta.setAttribute(\"content\", \""+v.getPreviewImageUrl()+"\");document.head.appendChild(meta);");
//			UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"og:image\");meta.setAttribute(\"content\", \""+v.getPreviewImageUrl()+"\");document.head.appendChild(meta);");
//			UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"og:image:url\");meta.setAttribute(\"content\", \""+v.getPreviewImageUrl()+"\");document.head.appendChild(meta)");
		
	    }
		
//	    Button share = new Button("Share on Whatsapp", VaadinIcon.SHARE.create());
//	    share.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
//	    share.addClickListener(v -> {
//	    	
//	    	UI.getCurrent().getPage().open("https://wa.me/?text="+url, "_blank");
//	    	
//	    });
//	    add(share);
	    
	    add(TweetButton.share("http://www.webnazar.com/status/"+tweetId).large().withHashtag("webnazar").withRelated("webnazar"));
	    
	    stitle = result.getData().getText().substring(0, result.getData().getText().indexOf("."));
    	getPageTitle();
	    
	    
//	    UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"name\", \"title\");meta.setAttribute(\"content\", \""+HtmlUtils.escape(result.getData().getText().substring(0, result.getData().getText().indexOf(".")))+"\");document.head.appendChild(meta);");
//	    UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"name\", \"description\");meta.setAttribute(\"content\", \"WebNazar - vision Thoroughly\");document.head.appendChild(meta);");
//	    UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"og:title\");meta.setAttribute(\"content\", \""+HtmlUtils.escape(result.getData().getText().substring(0, result.getData().getText().indexOf(".")))+"\");document.head.appendChild(meta);");
//        UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"og:description\");meta.setAttribute(\"content\", \"WebNazar - Vision Thoroughly\");document.head.appendChild(meta);");
//        UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"twitter:title\");meta.setAttribute(\"content\", \""+HtmlUtils.escape(result.getData().getText().substring(0, result.getData().getText().indexOf(".")))+"\");document.head.appendChild(meta);");
//        UI.getCurrent().getPage().executeJs("var meta = document.createElement('meta');meta.setAttribute(\"property\", \"twitter:description\");meta.setAttribute(\"content\", \"WebNazar - Vision Thoroughly\");document.head.appendChild(meta);");
        
        
        
		}
	
	}

	
	
	

