package latte.kr.com.project47seconds;

/**
 * Created by SK392 on 2017-07-30.
 */

public class MainNewsItem {
    String date;
    String title;
    String subtitle;
    String imageurl;

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    String newsUrl;

    public MainNewsItem(String date, String title, String subtitle, String imageurl, String newsUrl) {
        this.date = date;
        this.title = title;
        this.subtitle = subtitle;
        this.imageurl = imageurl;
        this.newsUrl = newsUrl;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
