package com.jedisWrapper.jedis;

import com.jedisWrapper.messaging.PubSub;
import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Only Jedis read commands are specified.
 * User: jasonhome
 * Date: 29/01/2012
 * Time: 22:33
 */
public interface BasicJedisCommands {

    Jedis getDecoratedResource();

    String ping();

    byte[] get(byte[] key);

    Boolean exists(byte[] key);

    String type(byte[] key);

    Set<String> keys(String pattern);

    String randomKey();

    Set<byte[]> keys(byte[] pattern);

    byte[] randomBinaryKey();

    Long ttl(byte[] key);

    String select(int index);

    List<byte[]> mget(byte[]... keys);

    byte[] substr(byte[] key, int start, int end);

    byte[] hget(byte[] key, byte[] field);

    List<byte[]> hmget(byte[] key, byte[]... fields);

    Boolean hexists(byte[] key, byte[] field);

    Long hlen(byte[] key);

    Set<byte[]> hkeys(byte[] key);

    List<byte[]> hvals(byte[] key);

    Map<byte[], byte[]> hgetAll(byte[] key);

    Long llen(byte[] key);

    List<byte[]> lrange(byte[] key, int start, int end);

    byte[] lindex(byte[] key, int index);

    String lset(byte[] key, int index, byte[] value);

    byte[] lpop(byte[] key);

    Set<byte[]> smembers(byte[] key);

    byte[] spop(byte[] key);

    Long scard(byte[] key);

    Boolean sismember(byte[] key, byte[] member);

    Set<byte[]> sinter(byte[]... keys);

    Set<byte[]> sunion(byte[]... keys);

    Set<byte[]> sdiff(byte[]... keys);

    byte[] srandmember(byte[] key);

    Long zadd(byte[] key, double score, byte[] member);

    Set<byte[]> zrange(byte[] key, int start, int end);

    Long zrank(byte[] key, byte[] member);

    Long zrevrank(byte[] key, byte[] member);

    Set<byte[]> zrevrange(byte[] key, int start, int end);

    Set<Tuple> zrangeWithScores(byte[] key, int start, int end);

    Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end);

    Long zcard(byte[] key);

    Double zscore(byte[] key, byte[] member);

    String quit();

    Long dbSize();

    void connect();

    void disconnect();

    List<byte[]> sort(byte[] key);

    List<byte[]> sort(byte[] key, SortingParams sortingParameters);

    Long sort(byte[] key, SortingParams sortingParameters, byte[] dstkey);

    Long sort(byte[] key, byte[] dstkey);

    List<Object> pipelined(PipelineBlock jedisPipeline);

    Long zcount(byte[] key, double min, double max);

    Set<byte[]> zrangeByScore(byte[] key, double min, double max);

    Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max);

    Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count);

    Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max);

    Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count);

    Set<byte[]> zrevrangeByScore(byte[] key, double max, double min);

    Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min);

    Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count);

    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min);

    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count);

    Long zunionstore(byte[] dstkey, byte[]... sets);

    Long zunionstore(byte[] dstkey, ZParams params, byte[]... sets);

    Long zinterstore(byte[] dstkey, byte[]... sets);

    Long zinterstore(byte[] dstkey, ZParams params, byte[]... sets);

    String shutdown();

    String info();

    void monitor(JedisMonitor jedisMonitor);

    String slaveof(String host, int port);

    String slaveofNoOne();

    List<String> configGet(String pattern);

    String configResetStat();

    String configSet(String parameter, String value);

    boolean isConnected();

    Long strlen(byte[] key);

    void sync();

    byte[] echo(byte[] string);

    String debug(DebugParams params);

    Client getClient();

    Long getbit(byte[] key, long offset);

    String getrange(byte[] key, long startOffset, long endOffset);

    Long getDB();

    List<String> mget(String... keys);

    Set<String> sinter(String... keys);

    Long sinterstore(String dstkey, String... keys);

    Set<String> sunion(String... keys);

    Set<String> sdiff(String... keys);

    Long sort(String key, SortingParams sortingParameters, String dstkey);

    Long sort(String key, String dstkey);

    Set<String> zrangeByScore(String key, String min, String max);

    Set<String> zrevrangeByScore(String key, String max, String min);

    Long strlen(String key);

    String echo(String string);

    String get(String s);

    Boolean exists(String s);

    String type(String s);

    Long ttl(String s);

    boolean getbit(String s, long l);

    String getrange(String s, long l, long l1);

    String substr(String s, int i, int i1);

    String hget(String s, String s1);

    List<String> hmget(String s, String... strings);

    Boolean hexists(String s, String s1);

    Long hlen(String s);

    Set<String> hkeys(String s);

    List<String> hvals(String s);

    Map<String, String> hgetAll(String s);

    Long llen(String s);

    List<String> lrange(String s, long l, long l1);

    String lindex(String s, long l);

    String lpop(String s);

    Set<String> smembers(String s);

    String spop(String s);

    Long scard(String s);

    Boolean sismember(String s, String s1);

    String srandmember(String s);

    Set<String> zrange(String s, int i, int i1);

    Long zrank(String s, String s1);

    Long zrevrank(String s, String s1);

    Set<String> zrevrange(String s, int i, int i1);

    Set<Tuple> zrangeWithScores(String s, int i, int i1);

    Set<Tuple> zrevrangeWithScores(String s, int i, int i1);

    Long zcard(String s);

    Double zscore(String s, String s1);

    List<String> sort(String s);

    List<String> sort(String s, SortingParams sortingParams);

    Long zcount(String s, double v, double v1);

    Set<String> zrangeByScore(String s, double v, double v1);

    Set<String> zrevrangeByScore(String s, double v, double v1);

    Set<String> zrangeByScore(String s, double v, double v1, int i, int i1);

    Set<String> zrevrangeByScore(String s, double v, double v1, int i, int i1);

    Set<Tuple> zrangeByScoreWithScores(String s, double v, double v1);

    Set<Tuple> zrevrangeByScoreWithScores(String s, double v, double v1);

    Set<Tuple> zrangeByScoreWithScores(String s, double v, double v1, int i, int i1);

    Set<Tuple> zrevrangeByScoreWithScores(String s, double v, double v1, int i, int i1);

    String auth(String password);

    void subscribe(PubSub jedisPubSub, String... channels);

    void psubscribe(PubSub jedisPubSub, String... patterns);

    Pipeline pipelined();

}
