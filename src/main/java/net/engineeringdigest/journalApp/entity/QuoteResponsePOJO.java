package net.engineeringdigest.journalApp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuoteResponsePOJO {
    private String quote;
    private String author;
    private String category;

}
