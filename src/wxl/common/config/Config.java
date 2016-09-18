package wxl.common.config;

import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import wxl.common.model._MappingKit;
import wxl.index.controller.IndexController;

/**
 * Created by wxl on 2016/09/14.
 */
public class Config extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setViewType(ViewType.JSP);
        constants.setBaseViewPath("WEB-INF/pages/");
        //加载配置文件
        PropKit.use("constant.properties");
    }

    @Override
    public void configRoute(Routes routes) {//路由
        routes.add("/", IndexController.class);
    }

    @Override
    public void configPlugin(Plugins plugins) {//插件
        //数据库连接
        Prop jdbc = PropKit.use("jdbc.properties");
        C3p0Plugin cp = new C3p0Plugin(jdbc.get("url"), jdbc.get("username"), jdbc.get("password"));
        cp.setDriverClass(jdbc.get("driverClassName"));
        plugins.add(cp);
        //ActiveRecord
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        plugins.add(arp);
        //oracle特性设置
        arp.setDialect(new OracleDialect());//方言
        arp.setContainerFactory(new CaseInsensitiveContainerFactory());//大小写不敏感

        //映射
        _MappingKit.mapping(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {//拦截器
    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
