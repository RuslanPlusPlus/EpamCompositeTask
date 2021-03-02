package by.me.composite.entity;

public enum ComponentType {
    TEXT("Text"),
    PARAGRAPH("Paragraph"),
    SENTENCE("Sentence"),
    LEXEME("Lexeme"),
    WORD("Word"),
    PUNCTUATION_MARK("Punctuation mark"),
    SYMBOL("Symbol");

    private String value;

    ComponentType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
