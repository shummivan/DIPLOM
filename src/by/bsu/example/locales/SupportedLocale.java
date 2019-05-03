package by.bsu.example.locales;


public enum SupportedLocale {
    ru{
        {
            this.languageName = "Русский";
            this.language = "ru";
            this.country = "RU";
        }
    },
    en{
        {
            this.languageName = "English";
            this.language = "en";
            this.country = "US";
        }
    };
    String languageName;
    String language;
    String country;
    public String getLanguageName() {
        return languageName;
    }
    public String getLanguage() {
        return language;
    }
    public String getCountry() { return country; }
}
