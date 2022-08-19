package com.eneuron.views.webnazar;


import org.vaadin.addon.twitter.Timeline;

import com.eneuron.views.MainLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("WEB NAZAR - Entertainment")
@Route(value = "entertainment", layout = MainLayout.class)
@CssImport(value = "./themes/webnazar/home.css", themeFor="vaadin-text-field")
public class EntertainmentView extends VerticalLayout implements AfterNavigationObserver {

	private TextField tfsearch;
	VerticalLayout vLResult;
	
	
    public EntertainmentView() {
        
    	
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
	}
    
	private void generateWhatsNewOnWeb() {
    	
    	
		Timeline timeline = Timeline.list("1554092850362855424").withHashtag("webnazar").withRelated("webnazar");
    	timeline.setWidthFull();
    	
    	vLResult.add(timeline);
    	
    	
    	
    }
}
