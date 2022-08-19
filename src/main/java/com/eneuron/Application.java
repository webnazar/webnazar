package com.eneuron;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Inline.Position;
import com.vaadin.flow.component.page.Inline.Wrapping;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "webnazar")
@PWA(name = "WebNazar", shortName = "WebNazar", offlineResources = {})
@NpmPackage(value = "line-awesome", version = "1.3.0")

public class Application extends SpringBootServletInitializer  implements AppShellConfigurator{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
    public void configurePage(AppShellSettings settings) {
    	
      settings.addMetaTag(Position.PREPEND, "author", "nazar");
      settings.addMetaTag(Position.PREPEND, "description", "WebNazar Vision Thoroughly - Trends, Technology, Science, Innovation, News, Education and more...");
      settings.addMetaTag(Position.PREPEND, "title", "WebNazar - Vision Thoroughly");
      
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"og:title\" content=\"WEBNAZAR - Vision Thoroughly\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"og:locale\" content=\"en\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"og:url\" content=\"http://www.webnazar.com\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"og:image\" content=\"http://www.webnazar.com/images/webnazar.png\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"og:image:url\" content=\"http://www.webnazar.com/images/webnazar.png\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"og:image:width\" content=\"1200\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"og:image:height\" content=\"627\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"og:type\" content=\"article\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"og:site_name\" content=\"WebNazar - Vision Thoroughly\">", Wrapping.NONE);
      //settings.addInlineWithContents(Position.PREPEND, "<meta property=\"og:description\" content=\"Trends, Technology, Science, Innovation, News, Education and more...\">", Wrapping.NONE);
      
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"twitter:title\" content=\"WEBNAZAR - Vision Thoroughly\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"twitter:description\" content=\"Trends, Technology, Science, Innovation, News, Education and more...\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"twitter:image\" content=\"http://www.webnazar.com/images/webnazar.png\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"twitter:site\" content=\"@webnazar\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"twitter:card\" content=\"summary_large_image\">", Wrapping.NONE);
      settings.addInlineWithContents(Position.PREPEND, "<meta property=\"twitter:domain\" content=\"http://www.webnazar.com\">", Wrapping.NONE);
      
      
      
    }

}
