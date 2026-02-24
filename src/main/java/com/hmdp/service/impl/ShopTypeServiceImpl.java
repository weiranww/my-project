package com.hmdp.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result queryTypeList() {
        Object o =new Object();
        JSONUtil.toJsonStr(o);
        String key = "TypeList";
        String type = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(type)){
            return Result.ok(JSONUtil.toList(type,ShopType.class));
        }
        List<ShopType> typeList = query().orderByAsc("sort").list();
        if(typeList == null){
            return Result.fail("网页错误");
        }
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(typeList));
        return Result.ok(typeList);
    }
}
