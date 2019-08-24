package com.spring.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.spring.pojo.SysUser;
import com.spring.response.UnicomResponseEnums;
import com.spring.response.UnicomRuntimeException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class RedisDao {

    private JedisPool jedisPool;

    private RuntimeSchema<SysUser> schema = RuntimeSchema.createFrom(SysUser.class);

    public RedisDao(String ip,int port){
        jedisPool = new JedisPool(ip,port);
    }
    public SysUser getSysUser(String username){
        //redis操作
        try {
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "userid:" + username;
                //反序列化得到我们想要的结果
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null){
                    //空对象
                    SysUser sysUser = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,sysUser,schema);
                    //sysUser被反序列化
                    return sysUser;
                }
            }finally {
                jedis.close();
            }
        } catch (Exception e){
            throw new UnicomRuntimeException(UnicomResponseEnums.SYSTEM_ERROR);
        }
        return null;
    }
    public String putSysUser(SysUser sysUser){
        try{
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "userid:" + sysUser.getUsername();
                byte[] bytes = ProtostuffIOUtil.toByteArray(sysUser,schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timout = 60 * 60;
                String result = jedis.setex(key.getBytes(),timout,bytes);
                return result;
            }finally {
                jedis.close();
            }
        }catch (Exception e){

        }
        return null;
    }
}
