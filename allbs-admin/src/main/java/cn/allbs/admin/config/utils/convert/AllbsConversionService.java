package cn.allbs.admin.config.utils.convert;

import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.lang.Nullable;

/**
 * 类 AllbsConversionService
 *
 * @author ChenQi
 * @date 2024/4/1
 */
public class AllbsConversionService extends DefaultFormattingConversionService {

    @Nullable
    private static volatile AllbsConversionService SHARED_INSTANCE;

    public AllbsConversionService() {
        super();
        super.addConverter(new EnumToStringConverter());
        super.addConverter(new StringToEnumConverter());
    }

    /**
     * Return a shared default application {@code ConversionService} instance, lazily
     * building it once needed.
     * <p>
     * Note: This method actually returns an {@link AllbsConversionService}
     * instance. However, the {@code ConversionService} signature has been preserved for
     * binary compatibility.
     *
     * @return the shared {@code AllbsConversionService} instance (never{@code null})
     */
    public static GenericConversionService getInstance() {
        AllbsConversionService sharedInstance = AllbsConversionService.SHARED_INSTANCE;
        if (sharedInstance == null) {
            synchronized (AllbsConversionService.class) {
                sharedInstance = AllbsConversionService.SHARED_INSTANCE;
                if (sharedInstance == null) {
                    sharedInstance = new AllbsConversionService();
                    AllbsConversionService.SHARED_INSTANCE = sharedInstance;
                }
            }
        }
        return sharedInstance;
    }
}
