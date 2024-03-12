package cn.allbs.admin.security.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类 MenuVO
 *
 * @author ChenQi
 * @date 2024/3/12
 */
@Data
@Schema(title = "菜单信息", name = "MenuVO")
public class MenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @Schema(description = "菜单id")
    private Long menuId;
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String name;
    /**
     * 菜单权限标识
     */
    @Schema(description = "菜单权限标识")
    private String permission;
    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单id")
    private Long parentId;
    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;
    /**
     * 前端路由标识路径
     */
    @Schema(description = "前端路由标识路径")
    private String path;
    /**
     * 排序值
     */
    @Schema(description = "排序值")
    private Integer sort;
    /**
     * 菜单类型 （0:目录 1:菜单 2:按钮）
     */
    @Schema(description = "菜单类型,0:目录 1:菜单 2:按钮")
    private String type;
    /**
     * 是否缓存
     */
    @Schema(description = "路由缓存")
    private String keepAlive;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    /**
     * 0--正常 1--删除
     */
    @Schema(description = "删除标记,1:已删除,0:正常")
    private String delFlg;

    @Override
    public int hashCode() {
        return menuId.hashCode();
    }

    /**
     * menuId 相同则相同
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MenuVO) {
            Long targetMenuId = ((MenuVO) obj).getMenuId();
            return menuId.equals(targetMenuId);
        }
        return super.equals(obj);
    }
}
