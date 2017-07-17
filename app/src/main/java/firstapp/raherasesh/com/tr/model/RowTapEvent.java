package firstapp.raherasesh.com.tr.model;

/**
 * Created by Sh-Java on 12/10/2016.
 */
public class RowTapEvent {
    private News newsRow;

    public RowTapEvent(News newsRow) {
        this.newsRow = newsRow;
    }

    public News getTestRow() {
        return newsRow;
    }
}
