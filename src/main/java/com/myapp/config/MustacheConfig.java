package com.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.samskivert.mustache.Mustache;

@Configuration
public class MustacheConfig {
	
	@Bean
    public Mustache.Compiler mustacheCompiler(Mustache.TemplateLoader templateLoader, Environment environment) {
        MustacheEnvironmentCollector collector = new MustacheEnvironmentCollector();
        collector.setEnvironment(environment);

        Mustache.Compiler compiler = Mustache.compiler()
            .defaultValue("")   // set default value
            .withLoader(templateLoader)
            .withCollector(collector);

        return compiler;
    }
}
