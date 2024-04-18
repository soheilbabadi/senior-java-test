package ir.smartpath.senior.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBooksApiResponse {

        private String kind;
        private long totalItems;
        private List<Item> items;

    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String kind;
        private String id;
        private String etag;
        private String selfLink;
        private VolumeInfo volumeInfo;
        private SaleInfo saleInfo;
        private AccessInfo accessInfo;
        private SearchInfo searchInfo;
    }


    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccessInfo {
        private String country;
        private String viewability;
        private boolean embeddable;
        private boolean publicDomain;
        private String textToSpeechPermission;
        private Epub epub;
        private Epub pdf;
        private String webReaderLink;
        private String accessViewStatus;
        private boolean quoteSharingAllowed;
    }

    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Epub {
        private boolean isAvailable;
        private String acsTokenLink;
    }


    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SaleInfo {
        private String country;
        private String saleability;
        private boolean isEbook;
        private SaleInfoListPrice listPrice;
        private SaleInfoListPrice retailPrice;
        private String buyLink;
        private List<Offer> offers;
    }


    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SaleInfoListPrice {
        private double amount;
        private String currencyCode;
    }


    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Offer {
        private long finskyOfferType;
        private OfferListPrice listPrice;
        private OfferListPrice retailPrice;
        private boolean giftable;
    }

    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OfferListPrice {
        private long amountInMicros;
        private String currencyCode;
    }

    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SearchInfo {
        private String textSnippet;
    }
    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        private String title;
        private String subtitle;
        private List<String> authors;
        private String publisher;
        private String publishedDate;
        private String description;
        private List<IndustryIdentifier> industryIdentifiers;
        private ReadingModes readingModes;
        private int pageCount;
        private String printType;
        private List<String> categories;
        private String maturityRating;
        private boolean allowAnonLogging;
        private String contentVersion;
        private PanelizationSummary panelizationSummary;
        private ImageLinks imageLinks;
        private String language;
        private String previewLink;
        private String infoLink;
        private String canonicalVolumeLink;
    }

    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImageLinks {
        private String smallThumbnail;
        private String thumbnail;
    }

    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndustryIdentifier {
        private String type;
        private String identifier;
    }



    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PanelizationSummary {
        private boolean containsEpubBubbles;
        private boolean containsImageBubbles;
    }


    @Data @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ReadingModes {
        private boolean text;
        private boolean image;
    }
}
