package wxl.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("ID", id);
	}

	public java.lang.String getId() {
		return get("ID");
	}

	public void setUsername(java.lang.String username) {
		set("USERNAME", username);
	}

	public java.lang.String getUsername() {
		return get("USERNAME");
	}

	public void setAvatar(java.lang.String avatar) {
		set("AVATAR", avatar);
	}

	public java.lang.String getAvatar() {
		return get("AVATAR");
	}

	public void setSign(java.lang.String sign) {
		set("SIGN", sign);
	}

	public java.lang.String getSign() {
		return get("SIGN");
	}

}
