package cn.allbs.admin.config.utils;

import cn.allbs.admin.config.utils.convert.AllbsConversionService;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

/**
 * 基于 spring ConversionService 类型转换
 *
 * @author ChenQi
 * @date 2024/4/1
 */
@SuppressWarnings("unchecked")
public class ConvertUtil {

    /**
     * Convenience operation for converting a source object to the specified targetType.
     * {@link TypeDescriptor#forObject(Object)}.
     *
     * @param source     the source object
     * @param targetType the target type
     * @param <T>        泛型标记
     * @return the converted value
     * @throws IllegalArgumentException if targetType is {@code null},
     *                                  or sourceType is {@code null} but source is not {@code null}
     */
    @Nullable
    public static <T> T convert(@Nullable Object source, Class<T> targetType) {
        if (source == null) {
            return null;
        }
        if (ClassUtils.isAssignableValue(targetType, source)) {
            return (T) source;
        }
        GenericConversionService conversionService = AllbsConversionService.getInstance();
        return conversionService.convert(source, targetType);
    }

    /**
     * Convenience operation for converting a source object to the specified targetType,
     * where the target type is a descriptor that provides additional conversion context.
     * {@link TypeDescriptor#forObject(Object)}.
     *
     * @param source     the source object
     * @param sourceType the source type
     * @param targetType the target type
     * @param <T>        泛型标记
     * @return the converted value
     * @throws IllegalArgumentException if targetType is {@code null},
     *                                  or sourceType is {@code null} but source is not {@code null}
     */
    @Nullable
    public static <T> T convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }
        GenericConversionService conversionService = AllbsConversionService.getInstance();
        return (T) conversionService.convert(source, sourceType, targetType);
    }

    /**
     * Convenience operation for converting a source object to the specified targetType,
     * where the target type is a descriptor that provides additional conversion context.
     * Simply delegates to {@link #convert(Object, TypeDescriptor, TypeDescriptor)} and
     * encapsulates the construction of the source type descriptor using
     * {@link TypeDescriptor#forObject(Object)}.
     *
     * @param source     the source object
     * @param targetType the target type
     * @param <T>        泛型标记
     * @return the converted value
     * @throws IllegalArgumentException if targetType is {@code null},
     *                                  or sourceType is {@code null} but source is not {@code null}
     */
    @Nullable
    public static <T> T convert(@Nullable Object source, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }
        GenericConversionService conversionService = AllbsConversionService.getInstance();
        return (T) conversionService.convert(source, targetType);
    }

    /**
     * Convenience operation for converting a source object to the specified targetType.
     * Simply delegates to {@link #convert(Object, Class)} and encapsulates any exceptions
     * in a {@link ConversionFailedException}.
     *
     * @param obj        the source object
     * @param targetType the target type
     * @param <T>        泛型标记
     * @return the converted value
     * @throws ConversionFailedException if the conversion fails
     */
    @Nullable
    public static <T> T convertObject(@Nullable Object obj, Class<T> targetType, T defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        try {
            return convert(obj, targetType);
        } catch (ConversionFailedException e) {
            return defaultValue;
        }
    }

}
