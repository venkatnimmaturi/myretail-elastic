package myretail.jest.client.gson.adapter;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.util.Assert;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MoneyAdapter implements JsonDeserializer<Money>, JsonSerializer<Money> {

	@Override
	public JsonElement serialize(Money src, Type typeOfSrc, JsonSerializationContext context) {

		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("value", src.getAmount());
		jsonObject.addProperty("currency_code", src.getCurrencyUnit().toString());

		return jsonObject;
	}

	@Override
	public Money deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObj = json.getAsJsonObject();
		Assert.notNull(jsonObj.get("currency_code"), "Currency code cannot be null");
		Assert.notNull(jsonObj.get("value"), "Price cannot be null");
		CurrencyUnit currency = CurrencyUnit.of(jsonObj.get("currency_code").getAsString());
		BigDecimal value = jsonObj.get("value").getAsBigDecimal();
		return Money.of(currency, value);
	}

}
