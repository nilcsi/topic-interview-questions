package com.brandwatch.interviews.topic;

import java.io.File;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

import com.brandwatch.interviews.topic.ascii.AsciiTitleGenerator;

@SpringBootApplication(
        scanBasePackages = "com.brandwatch.interviews.topic"
)
@RequiredArgsConstructor
public class Main implements CommandLineRunner {

    private final AsciiTitleGenerator titleGenerator;
    private final Demo demo;

    @Parameter(names = "-limit", required = true)
    private int limit;

    @Parameter(names = "-input", converter = FileConverter.class)
    private File file;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Topic Extraction Demo");
        JCommander jcommander = JCommander.newBuilder().addObject(this).build();
        jcommander.parse(args);
        System.out.println(titleGenerator.buildTitle());
        demo.runDemo(file, limit);
    }
}
