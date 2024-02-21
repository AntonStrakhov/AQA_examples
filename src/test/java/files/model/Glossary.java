package files.model;

import com.google.gson.annotations.SerializedName;

public class Glossary {
    public String title;
    @SerializedName("Gloss_div")
    public GlossDiv glossDiv;

    public static class GlossDiv {
        public String title;
        public Boolean flag;
    }
}