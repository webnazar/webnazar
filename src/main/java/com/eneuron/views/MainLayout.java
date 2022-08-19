package com.eneuron.views;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.vaadin.addon.twitter.TweetButton;

import com.eneuron.util.Constant;
import com.eneuron.views.about.AboutView;
import com.eneuron.views.other.PrivacyPolicyView;
import com.eneuron.views.other.TermsofServiceView;
import com.eneuron.views.webnazar.ComedyView;
import com.eneuron.views.webnazar.EducationView;
import com.eneuron.views.webnazar.EntertainmentView;
import com.eneuron.views.webnazar.HomeView;
import com.eneuron.views.webnazar.JobsView;
import com.eneuron.views.webnazar.MoviesView;
import com.eneuron.views.webnazar.NatureView;
import com.eneuron.views.webnazar.NewsView;
import com.eneuron.views.webnazar.ScienceView;
import com.eneuron.views.webnazar.SportsView;
import com.eneuron.views.webnazar.TechnologyView;
import com.eneuron.views.webnazar.TravelView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.shared.ui.LoadMode;
import com.vaadin.flow.theme.lumo.LumoUtility;


/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    /**
     * A simple navigation item component, based on ListItem element.
     */
	
	
	public static String slanguage = "en";
	public Button language;
	
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, String iconClass, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            link.addClassNames("menu-item-link");
            link.setRoute(view);

            Span text = new Span(menuTitle);
            text.addClassNames("menu-item-text");

            link.add(new LineAwesomeIcon(iconClass), text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

        /**
         * Simple wrapper to create icons using LineAwesome iconset. See
         * https://icons8.com/line-awesome
         */
        @NpmPackage(value = "line-awesome", version = "1.3.0")
        public static class LineAwesomeIcon extends Span {
            public LineAwesomeIcon(String lineawesomeClassnames) {
                addClassNames("menu-item-icon");
                if (!lineawesomeClassnames.isEmpty()) {
                    addClassNames(lineawesomeClassnames);
                }
            }
        }

    }

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassNames("view-toggle");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("view-title");

        Header header = new Header(toggle, viewTitle);
        header.addClassNames("view-header");
        return header;
    }

    private Component createDrawerContent() {
    	
    	HorizontalLayout layout = new HorizontalLayout();
    	layout.setWidthFull();
    	layout.setPadding(true);
    	
    	Image logo = new Image("images/webnazar-text.png", "WEBNAZAR");
    	logo.setHeight("48px");
    	layout.add(logo);
    	logo.addClickListener(v -> {
        	
        	UI.getCurrent().getPage().open("/webnazar", "_self");
        	
        });
    	
        //H2 appName = new H2("WEB NAZAR");
        //appName.addClassNames("app-name");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(layout,
                createNavigation(), createFooter());
        section.addClassNames("drawer-section");
        return section;
    }

    private Nav createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("menu-item-container");
        nav.getElement().setAttribute("aria-labelledby", "views");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("navigation-list");
        nav.add(list);

        int i=1;
        for (MenuItemInfo menuItem : createMenuItems()) {
            
        	if(i==2 || i==13) {
        		
        		list.add(new Hr());
        	}
        	
        	list.add(menuItem);
        	
        	i++;

        }
        
        
        list.add(new Hr());
       
        VerticalLayout vlayout = new VerticalLayout();
        vlayout.setSpacing(false);
        vlayout.setMargin(false);
        vlayout.setPadding(false);
        vlayout.addClassName("pl-s");
        vlayout.addClassName("pr-s");
        list.add(vlayout);
        
        TweetButton follow = TweetButton.follow("WebNazar").hideScreenName().withCount(TweetButton.Count.none);
        //follow.setWidth("100%");
        
        vlayout.add(follow);
        
        return nav;
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //
                
        		new MenuItemInfo("Home", "la la-home", HomeView.class), //
        		
        		new MenuItemInfo("News", "la la-globe", NewsView.class), //
        		
        		new MenuItemInfo("Education", "la la-school", EducationView.class), //
        		
        		new MenuItemInfo("Sports", "la la-volleyball-ball", SportsView.class), //
        		
        		new MenuItemInfo("Technology", "la la-microchip", TechnologyView.class), //
        		
        		new MenuItemInfo("Jobs & Interview", "la la-bell", JobsView.class), //
        		
        		new MenuItemInfo("Science", "la la-flask", ScienceView.class), //
        		
        		new MenuItemInfo("Movies", "la la-youtube", MoviesView.class), //
        		
        		new MenuItemInfo("Travel", "la la-plane", TravelView.class), //
        		
        		new MenuItemInfo("Nature", "la la-globe", NatureView.class), //
        		
        		new MenuItemInfo("Entertainment", "la la-video", EntertainmentView.class), //
        		
        		new MenuItemInfo("Comedy", "la la-theater-masks", ComedyView.class), //

                new MenuItemInfo("About Us", "la la-eye", AboutView.class), //
                
                new MenuItemInfo("Privacy Policy", "la la-universal-access", PrivacyPolicyView.class), //
                
                new MenuItemInfo("Terms of Service", "la la-universal-access", TermsofServiceView.class), //

        };
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        
        layout.addClassNames("footer");
        
//        Optional<User> maybeUser = authenticatedUser.get();
//        if (maybeUser.isPresent()) {
//            User user = maybeUser.get();
//
//            Avatar avatar = new Avatar(user.getName(), user.getProfilePictureUrl());
//            avatar.addClassNames("me-xs");
//
//            ContextMenu userMenu = new ContextMenu(avatar);
//            userMenu.setOpenOnClick(true);
//            userMenu.addItem("Logout", e -> {
//                authenticatedUser.logout();
//            });
//
//            Span name = new Span(user.getName());
//            name.addClassNames("font-medium", "text-s", "text-secondary");
//
//            layout.add(avatar, name);
//        } else {
//            Anchor loginLink = new Anchor("login", "Sign in");
//            layout.add(loginLink);
//        }
        
        //Anchor loginLink = new Anchor("https://twitter.com/i/oauth2/authorize?response_type=code&client_id=UDV2cmRoUnd0bF9CcjVwbTFfOWk6MTpjaQ&redirect_uri=http://www.webnazar.com/admin&scope=tweet.read%20users.read%20follows.read%20follows.write&state=state&code_challenge=challenge&code_challenge_method=plain", new Image("images/sign-in-with-twitter.png", "Twitter Sign In"));
        //Anchor loginLink = new Anchor("https://twitter.com/i/oauth2/authorize?response_type=code&client_id=UDV2cmRoUnd0bF9CcjVwbTFfOWk6MTpjaQ&redirect_uri=http://localhost:8080/admin&scope=tweet.read%20users.read%20follows.read%20follows.write&state=state&code_challenge=challenge&code_challenge_method=plain", new Image("images/sign-in-with-twitter.png", "Twitter Sign In"));
        
//        Anchor loginLink = new Anchor(getTwitterAuthorationUrl(), new Image("images/sign-in-with-twitter.png", "Twitter Sign In"));
//        layout.add(loginLink);
        
//        
        
        Div div = new Div();
        div.setId("translate");
        div.setWidthFull();
               
        layout.add(div);
        
        UI.getCurrent().getPage().executeJs("var el = document.getElementById('translate'); el.innerHTML = '<div id=\"google_translate_element\"></div>';var newScript = document.createElement(\"script\"); var inlineScript = document.createTextNode(\"function googleTranslateElementInit() {new google.translate.TranslateElement({pageLanguage: 'en'},'google_translate_element');}\"); newScript.appendChild(inlineScript);el.appendChild(newScript);var sc = document.createElement(\"script\"); sc.setAttribute(\"src\", \"https://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit\");el.appendChild(sc);");
        
//        HorizontalLayout hlayout = new HorizontalLayout();
//        hlayout.setPadding(false);
//        hlayout.setSpacing(false);
//        hlayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
//        layout.add(hlayout);
//                
//        Icon icon = new Icon(VaadinIcon.GLOBE);
//        icon.addClassName(LumoUtility.IconSize.SMALL);
//        icon.setColor(LumoUtility.TextColor.PRIMARY);
//        hlayout.add(icon);
//        
//        language = new Button(Constant.languageMap.get("en"));
//        language.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SMALL);
//        language.addClickListener(v -> {
//        	
//        	openLanguageDialog();
//        	
//        });
//        hlayout.add(language);


        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
        
        getLanguageCookie();
 
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "WEBNAZAR - Vision Thoroughly" : title.value();
    }
    
    private void updateLanguageCookie(String language) {
    	
      for(Map.Entry<String, String> map: Constant.languageMap.entrySet()) {
      	
    	  if(map.getValue().equals(language)) {
    		  
    		  slanguage = map.getKey();
    		  
    		  Cookie myCookie = new Cookie("language", map.getKey());
    		  myCookie.setMaxAge(43200);
    		  myCookie.setPath(VaadinService.getCurrentRequest().getContextPath());
    		  VaadinService.getCurrentResponse().addCookie(myCookie);
    		  
    		  break;
    	  }
      	
      }
     	
 	   
 	 UI.getCurrent().getPage().reload();
 	 
    }
    
    private void openLanguageDialog() {
    	
    	Dialog dialog = new Dialog();

    	dialog.setHeaderTitle("Change tweet widget language");

    	VerticalLayout dialogLayout = new VerticalLayout();
    	//dialogLayout.setWidth("400px");
    	//dialogLayout.setHeight("100%");
    	dialog.add(dialogLayout);
    	
    	RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
    	radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
    	radioGroup.setLabel("Travel class");
    	radioGroup.setItems(Constant.languageMap.values());
    	radioGroup.setValue(getLanguageName());
    	
    	radioGroup.addValueChangeListener(v -> {
    		
    		updateLanguageCookie(v.getValue());
    		
    	});
    	
    	dialogLayout.add(radioGroup);
    	
    	Button closeButton = new Button(new Icon("lumo", "cross"), (e) -> dialog.close());
    	closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    	dialog.getHeader().add(closeButton);

    	dialog.open();
    }
    
    private void getLanguageCookie() {
		
    	    	
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		
		for(int i=0;i<cookies.length;i++) {
			   
			   if(cookies[i].getName().equals("language")) {
				   
				   slanguage = cookies[i].getValue();
				   
				   language.setText(getLanguageName());
				   
				   break;
			   }
		}
		
		
	}
    
    private String getLanguageName() {
	
    	for(Map.Entry<String, String> map: Constant.languageMap.entrySet()) {
      	
    	  if(map.getKey().equals(slanguage)) {
    		  
    		  return map.getValue();
    	  }
      	
      }
    	
    	return Constant.languageMap.get("en");
		
	}
}
