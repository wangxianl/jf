package wxl.index.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import wxl.common.model.User;

import java.util.List;

/**
 * Created by wxl on 2016/09/14.
 * 首页
 */
public class IndexController extends Controller {
    /**
     * 首页路径
     */
    public void index() {
        List<User> users = User.dao.find("select * from t_user");
        User.dao.findById(1).toString();
        setAttr("users", JsonKit.toJson(users));
        render("index.jsp");
    }
}
