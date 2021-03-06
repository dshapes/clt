public class Data{

	@SerializedName("trending-product")
	private List<TrendingProductItem> trendingProduct;

	@SerializedName("slider")
	private List<String> slider;

	@SerializedName("category-list")
	private List<CategoryListItem> categoryList;

	@SerializedName("banner")
	private List<String> banner;

	@SerializedName("flash-sale")
	private List<FlashSaleItem> flashSale;

	@SerializedName("occasion-wear")
	private List<OccasionWearItem> occasionWear;

	@SerializedName("bottomBanner")
	private String bottomBanner;

	public List<TrendingProductItem> getTrendingProduct(){
		return trendingProduct;
	}

	public List<String> getSlider(){
		return slider;
	}

	public List<CategoryListItem> getCategoryList(){
		return categoryList;
	}

	public List<String> getBanner(){
		return banner;
	}

	public List<FlashSaleItem> getFlashSale(){
		return flashSale;
	}

	public List<OccasionWearItem> getOccasionWear(){
		return occasionWear;
	}

	public String getBottomBanner(){
		return bottomBanner;
	}
}
