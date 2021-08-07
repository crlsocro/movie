package no.kristiania.movie.site.Frontend.Controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SearchController implements Serializable {
    String selection;
    String query;

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSearchLink() {
        return "index?searchBy=" + selection + "&query=" + query + "&faces-redirect=true";
    }
}
