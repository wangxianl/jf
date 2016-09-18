package wxl.common.config;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

import javax.sql.DataSource;

/**
 * Created by wxl on 2016/09/18.
 * 生成
 */
public class ModelGenerator {
    public static DataSource getDataSource() {
        Prop jdbc = PropKit.use("jdbc.properties");
        C3p0Plugin c3p0Plugin = new C3p0Plugin(jdbc.get("url"), jdbc.get("username"), jdbc.get("password"));
        c3p0Plugin.setDriverClass(jdbc.get("driverClassName"));
        c3p0Plugin.start();
        return c3p0Plugin.getDataSource();
    }

    public static void main(String[] args) {
        // base model 所使用的包名
        String baseModelPackageName = "wxl.common.model.base";
        // base model 文件保存路径
        String baseModelOutputDir = PathKit.getWebRootPath() + "/../src/wxl/common/model/base";

        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "wxl.common.model";
        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = baseModelOutputDir + "/..";

        // 创建生成器
        Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
        // 设置数据库方言
        gernerator.setDialect(new OracleDialect());
        // 添加不需要生成的表名
//        gernerator.addExcludedTable("adv");
        // 设置是否在 Model 中生成 dao 对象
        gernerator.setGenerateDaoInModel(true);
        // 设置是否生成字典文件
        gernerator.setGenerateDataDictionary(true);
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        gernerator.setRemovedTableNamePrefixes("T_");
        // 生成
        gernerator.generate();
    }
}
