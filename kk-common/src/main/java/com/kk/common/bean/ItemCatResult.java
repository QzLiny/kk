package com.kk.common.bean;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @ClassName ItemCatResult
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/5 13:01
 */
public class ItemCatResult {
	/**
	 * 指定序列化json后的名称
	 */
	@JsonProperty("data")
	private List<ItemCatData> itemCats = new ArrayList<ItemCatData>();

	public List<ItemCatData> getItemCats() {
		return itemCats;
	}

	public void setItemCats(List<ItemCatData> itemCats) {
		this.itemCats = itemCats;
	}

}
