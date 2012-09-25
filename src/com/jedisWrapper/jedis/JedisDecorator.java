package com.jedisWrapper.jedis;

import com.google.inject.Inject;
import com.jedisWrapper.messaging.PubSub;
import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis decorator that implements AllJedisCommands
 * User: jasonhome
 * Date: 10/01/2012
 * Time: 16:20
 */
@SuppressWarnings("UnusedDeclaration")  //This is a decorator class so all AllJedisCommands have to be implemented
// to provide comprehensive delegation
public final class JedisDecorator implements AllJedisCommands {

    private final Jedis jedis;

    @Inject
    public JedisDecorator(Jedis jedis) {
        this.jedis = jedis;
    }


    public Jedis getDecoratedResource() {
        return jedis;
    }

    public String ping() {
        return jedis.ping();
    }

    public String set(byte[] key, byte[] value) {
        return jedis.set(key, value);
    }

    public byte[] get(byte[] key) {
        return jedis.get(key);
    }

    public Boolean exists(byte[] key) {
        return jedis.exists(key);
    }

    public Long del(byte[]... keys) {
        return jedis.del(keys);
    }

    public String type(byte[] key) {
        return jedis.type(key);
    }

    public Set<String> keys(String pattern) {
        return jedis.keys(pattern);
    }

    public String randomKey() {
        return jedis.randomKey();
    }

    public String rename(String oldkey, String newkey) {
        return jedis.rename(oldkey, newkey);
    }

    public Long renamenx(String oldkey, String newkey) {
        return jedis.renamenx(oldkey, newkey);
    }

    public Set<byte[]> keys(byte[] pattern) {
        return jedis.keys(pattern);
    }

    public byte[] randomBinaryKey() {
        return jedis.randomBinaryKey();
    }

    public String rename(byte[] oldkey, byte[] newkey) {
        return jedis.rename(oldkey, newkey);
    }

    public Long renamenx(byte[] oldkey, byte[] newkey) {
        return jedis.renamenx(oldkey, newkey);
    }

    public Long expire(byte[] key, int seconds) {
        return jedis.expire(key, seconds);
    }

    public Long expireAt(byte[] key, long unixTime) {
        return jedis.expireAt(key, unixTime);
    }

    public Long ttl(byte[] key) {
        return jedis.ttl(key);
    }

    public String select(int index) {
        return jedis.select(index);
    }

    public Long move(String key, int dbIndex) {
        return jedis.move(key, dbIndex);
    }

    public Long move(byte[] key, int dbIndex) {
        return jedis.move(key, dbIndex);
    }

    public String flushAll() {
        return jedis.flushAll();
    }

    public byte[] getSet(byte[] key, byte[] value) {
        return jedis.getSet(key, value);
    }

    public List<byte[]> mget(byte[]... keys) {
        return jedis.mget(keys);
    }

    public Long setnx(byte[] key, byte[] value) {
        return jedis.setnx(key, value);
    }

    public String setex(byte[] key, int seconds, byte[] value) {
        return jedis.setex(key, seconds, value);
    }

    public String mset(byte[]... keysvalues) {
        return jedis.mset(keysvalues);
    }

    public Long msetnx(byte[]... keysvalues) {
        return jedis.msetnx(keysvalues);
    }

    public Long decrBy(byte[] key, long integer) {
        return jedis.decrBy(key, integer);
    }

    public Long decr(byte[] key) {
        return jedis.decr(key);
    }

    public Long incrBy(byte[] key, long integer) {
        return jedis.incrBy(key, integer);
    }

    public Long incr(byte[] key) {
        return jedis.incr(key);
    }

    public Long append(byte[] key, byte[] value) {
        return jedis.append(key, value);
    }

    public byte[] substr(byte[] key, int start, int end) {
        return jedis.substr(key, start, end);
    }

    public Long hset(byte[] key, byte[] field, byte[] value) {
        return jedis.hset(key, field, value);
    }

    public byte[] hget(byte[] key, byte[] field) {
        return jedis.hget(key, field);
    }

    public Long hsetnx(byte[] key, byte[] field, byte[] value) {
        return jedis.hsetnx(key, field, value);
    }

    public String hmset(byte[] key, Map<byte[], byte[]> hash) {
        return jedis.hmset(key, hash);
    }

    public List<byte[]> hmget(byte[] key, byte[]... fields) {
        return jedis.hmget(key, fields);
    }

    public Long hincrBy(byte[] key, byte[] field, long value) {
        return jedis.hincrBy(key, field, value);
    }

    public Boolean hexists(byte[] key, byte[] field) {
        return jedis.hexists(key, field);
    }

    public Long hdel(byte[] key, byte[] field) {
        return jedis.hdel(key, field);
    }

    public Long hlen(byte[] key) {
        return jedis.hlen(key);
    }

    public Set<byte[]> hkeys(byte[] key) {
        return jedis.hkeys(key);
    }

    public List<byte[]> hvals(byte[] key) {
        return jedis.hvals(key);
    }

    public Map<byte[], byte[]> hgetAll(byte[] key) {
        return jedis.hgetAll(key);
    }

    public Long rpush(byte[] key, byte[] string) {
        return jedis.rpush(key, string);
    }

    public Long lpush(byte[] key, byte[] string) {
        return jedis.lpush(key, string);
    }

    public Long llen(byte[] key) {
        return jedis.llen(key);
    }

    public List<byte[]> lrange(byte[] key, int start, int end) {
        return jedis.lrange(key, start, end);
    }

    public String ltrim(byte[] key, int start, int end) {
        return jedis.ltrim(key, start, end);
    }

    public byte[] lindex(byte[] key, int index) {
        return jedis.lindex(key, index);
    }

    public String lset(byte[] key, int index, byte[] value) {
        return jedis.lset(key, index, value);
    }

    public Long lrem(byte[] key, int count, byte[] value) {
        return jedis.lrem(key, count, value);
    }

    public byte[] lpop(byte[] key) {
        return jedis.lpop(key);
    }

    public byte[] rpop(byte[] key) {
        return jedis.rpop(key);
    }

    public byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
        return jedis.rpoplpush(srckey, dstkey);
    }

    public Long sadd(byte[] key, byte[] member) {
        return jedis.sadd(key, member);
    }

    public Set<byte[]> smembers(byte[] key) {
        return jedis.smembers(key);
    }

    public Long srem(byte[] key, byte[] member) {
        return jedis.srem(key, member);
    }

    public byte[] spop(byte[] key) {
        return jedis.spop(key);
    }

    public Long smove(byte[] srckey, byte[] dstkey, byte[] member) {
        return jedis.smove(srckey, dstkey, member);
    }

    public Long scard(byte[] key) {
        return jedis.scard(key);
    }

    public Boolean sismember(byte[] key, byte[] member) {
        return jedis.sismember(key, member);
    }

    public Set<byte[]> sinter(byte[]... keys) {
        return jedis.sinter(keys);
    }

    public Long sinterstore(byte[] dstkey, byte[]... keys) {
        return jedis.sinterstore(dstkey, keys);
    }

    public Set<byte[]> sunion(byte[]... keys) {
        return jedis.sunion(keys);
    }

    public Long sunionstore(byte[] dstkey, byte[]... keys) {
        return jedis.sunionstore(dstkey, keys);
    }

    public Set<byte[]> sdiff(byte[]... keys) {
        return jedis.sdiff(keys);
    }

    public Long sdiffstore(byte[] dstkey, byte[]... keys) {
        return jedis.sdiffstore(dstkey, keys);
    }

    public byte[] srandmember(byte[] key) {
        return jedis.srandmember(key);
    }

    public Long zadd(byte[] key, double score, byte[] member) {
        return jedis.zadd(key, score, member);
    }

    public Set<byte[]> zrange(byte[] key, int start, int end) {
        return jedis.zrange(key, start, end);
    }

    public Long zrem(byte[] key, byte[] member) {
        return jedis.zrem(key, member);
    }

    public Double zincrby(byte[] key, double score, byte[] member) {
        return jedis.zincrby(key, score, member);
    }

    public Long zrank(byte[] key, byte[] member) {
        return jedis.zrank(key, member);
    }

    public Long zrevrank(byte[] key, byte[] member) {
        return jedis.zrevrank(key, member);
    }

    public Set<byte[]> zrevrange(byte[] key, int start, int end) {
        return jedis.zrevrange(key, start, end);
    }

    public Set<Tuple> zrangeWithScores(byte[] key, int start, int end) {
        return jedis.zrangeWithScores(key, start, end);
    }

    public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end) {
        return jedis.zrevrangeWithScores(key, start, end);
    }

    public Long zcard(byte[] key) {
        return jedis.zcard(key);
    }

    public Double zscore(byte[] key, byte[] member) {
        return jedis.zscore(key, member);
    }

    public Transaction multi() {
        return jedis.multi();
    }

    public List<Object> multi(TransactionBlock jedisTransaction) {
        return jedis.multi(jedisTransaction);
    }

    public String flushDB() {
            return jedis.flushDB();
        }

    public String quit() {
        return jedis.quit();
    }

    public Long dbSize() {
        return jedis.dbSize();
    }

    public void connect() {
        jedis.connect();
    }

    public void disconnect() {
        jedis.disconnect();
    }

    public String watch(byte[]... keys) {
        return jedis.watch(keys);
    }

    public String unwatch() {
        return jedis.unwatch();
    }

    public List<byte[]> sort(byte[] key) {
        return jedis.sort(key);
    }

    public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
        return jedis.sort(key, sortingParameters);
    }

    public List<byte[]> blpop(int timeout, byte[]... keys) {
        return jedis.blpop(timeout, keys);
    }

    public Long sort(byte[] key, SortingParams sortingParameters, byte[] dstkey) {
        return jedis.sort(key, sortingParameters, dstkey);
    }

    public Long sort(byte[] key, byte[] dstkey) {
        return jedis.sort(key, dstkey);
    }

    public List<byte[]> brpop(int timeout, byte[]... keys) {
        return jedis.brpop(timeout, keys);
    }

    public List<Object> pipelined(PipelineBlock jedisPipeline) {
        return jedis.pipelined(jedisPipeline);
    }

    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        jedis.subscribe(jedisPubSub, channels);
    }

    public Long publish(String channel, String message) {
        return jedis.publish(channel, message);
    }

    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        jedis.psubscribe(jedisPubSub, patterns);
    }

    public Long zcount(byte[] key, double min, double max) {
        return jedis.zcount(key, min, max);
    }

    public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
        return jedis.zrangeByScore(key, min, max);
    }

    public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max) {
        return jedis.zrangeByScore(key, min, max);
    }

    public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
        return jedis.zrangeByScore(key, min, max, offset, count);
    }

    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
        return jedis.zrangeByScoreWithScores(key, min, max);
    }

    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
        return jedis.zrevrangeByScore(key, max, min);
    }

    public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min) {
        return jedis.zrevrangeByScore(key, max, min);
    }

    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        return jedis.zrevrangeByScore(key, max, min, offset, count);
    }

    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        return jedis.zrevrangeByScoreWithScores(key, max, min);
    }

    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    public Long zremrangeByRank(byte[] key, int start, int end) {
        return jedis.zremrangeByRank(key, start, end);
    }

    public Long zremrangeByScore(byte[] key, double start, double end) {
        return jedis.zremrangeByScore(key, start, end);
    }

    public Long zunionstore(byte[] dstkey, byte[]... sets) {
        return jedis.zunionstore(dstkey, sets);
    }

    public Long zunionstore(byte[] dstkey, ZParams params, byte[]... sets) {
        return jedis.zunionstore(dstkey, params, sets);
    }

    public Long zinterstore(byte[] dstkey, byte[]... sets) {
        return jedis.zinterstore(dstkey, sets);
    }

    public Long zinterstore(byte[] dstkey, ZParams params, byte[]... sets) {
        return jedis.zinterstore(dstkey, params, sets);
    }

    public String save() {
        return jedis.save();
    }

    public String bgsave() {
        return jedis.bgsave();
    }

    public String bgrewriteaof() {
        return jedis.bgrewriteaof();
    }

    public Long lastsave() {
        return jedis.lastsave();
    }

    public String shutdown() {
        return jedis.shutdown();
    }

    public String info() {
        return jedis.info();
    }

    public void monitor(JedisMonitor jedisMonitor) {
        jedis.monitor(jedisMonitor);
    }

    public String slaveof(String host, int port) {
        return jedis.slaveof(host, port);
    }

    public String slaveofNoOne() {
        return jedis.slaveofNoOne();
    }

    public List<String> configGet(String pattern) {
        return jedis.configGet(pattern);
    }

    public String configResetStat() {
        return jedis.configResetStat();
    }

    public String configSet(String parameter, String value) {
        return jedis.configSet(parameter, value);
    }

    public boolean isConnected() {
        return jedis.isConnected();
    }

    public Long strlen(byte[] key) {
        return jedis.strlen(key);
    }

    public void sync() {
        jedis.sync();
    }

    public Long lpushx(byte[] key, byte[] string) {
        return jedis.lpushx(key, string);
    }

    public Long persist(byte[] key) {
        return jedis.persist(key);
    }

    public Long rpushx(byte[] key, byte[] string) {
        return jedis.rpushx(key, string);
    }

    public byte[] echo(byte[] string) {
        return jedis.echo(string);
    }

    public Long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value) {
        return jedis.linsert(key, where, pivot, value);
    }

    public String debug(DebugParams params) {
        return jedis.debug(params);
    }

    public Client getClient() {
        return jedis.getClient();
    }

    public byte[] brpoplpush(byte[] source, byte[] destination, int timeout) {
        return jedis.brpoplpush(source, destination, timeout);
    }

    public Long setbit(byte[] key, long offset, byte[] value) {
        return jedis.setbit(key, offset, value);
    }

    public Long getbit(byte[] key, long offset) {
        return jedis.getbit(key, offset);
    }

    public long setrange(byte[] key, long offset, byte[] value) {
        return jedis.setrange(key, offset, value);
    }

    public String getrange(byte[] key, long startOffset, long endOffset) {
        return jedis.getrange(key, startOffset, endOffset);
    }

    public Long publish(byte[] channel, byte[] message) {
        return jedis.publish(channel, message);
    }

    public void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels) {
        jedis.subscribe(jedisPubSub, channels);
    }

    public void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns) {
        jedis.psubscribe(jedisPubSub, patterns);
    }

    public Long getDB() {
        return jedis.getDB();
    }

    public Long del(String... keys) {
        return jedis.del(keys);
    }

    public List<String> mget(String... keys) {
        return jedis.mget(keys);
    }

    public String mset(String... keysvalues) {
        return jedis.mset(keysvalues);
    }

    public Long msetnx(String... keysvalues) {
        return jedis.msetnx(keysvalues);
    }

    public String rpoplpush(String srckey, String dstkey) {
        return jedis.rpoplpush(srckey, dstkey);
    }

    public Long smove(String srckey, String dstkey, String member) {
        return jedis.smove(srckey, dstkey, member);
    }

    public Set<String> sinter(String... keys) {
        return jedis.sinter(keys);
    }

    public Long sinterstore(String dstkey, String... keys) {
        return jedis.sinterstore(dstkey, keys);
    }

    public Set<String> sunion(String... keys) {
        return jedis.sunion(keys);
    }

    public Long sunionstore(String dstkey, String... keys) {
        return jedis.sunionstore(dstkey, keys);
    }

    public Set<String> sdiff(String... keys) {
        return jedis.sdiff(keys);
    }

    public Long sdiffstore(String dstkey, String... keys) {
        return jedis.sdiffstore(dstkey, keys);
    }

    public String watch(String... keys) {
        return jedis.watch(keys);
    }

    public List<String> blpop(int timeout, String... keys) {
        return jedis.blpop(timeout, keys);
    }

    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        return jedis.sort(key, sortingParameters, dstkey);
    }

    public Long sort(String key, String dstkey) {
        return jedis.sort(key, dstkey);
    }

    public List<String> brpop(int timeout, String... keys) {
        return jedis.brpop(timeout, keys);
    }

    public Set<String> zrangeByScore(String key, String min, String max) {
        return jedis.zrangeByScore(key, min, max);
    }

    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return jedis.zrevrangeByScore(key, max, min);
    }

    public Long zunionstore(String dstkey, String... sets) {
        return jedis.zunionstore(dstkey, sets);
    }

    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        return jedis.zunionstore(dstkey, params, sets);
    }

    public Long zinterstore(String dstkey, String... sets) {
        return jedis.zinterstore(dstkey, sets);
    }

    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        return jedis.zinterstore(dstkey, params, sets);
    }

    public Long strlen(String key) {
        return jedis.strlen(key);
    }

    public Long lpushx(String key, String string) {
        return jedis.lpushx(key, string);
    }

    public Long persist(String key) {
        return jedis.persist(key);
    }

    public Long rpushx(String key, String string) {
        return jedis.rpushx(key, string);
    }

    public String echo(String string) {
        return jedis.echo(string);
    }

    public String brpoplpush(String source, String destination, int timeout) {
        return jedis.brpoplpush(source, destination, timeout);
    }

    public String set(String s, String s1) {
        return jedis.set(s, s1);
    }

    public String get(String s) {
        return jedis.get(s);
    }

    public Boolean exists(String s) {
        return jedis.exists(s);
    }

    public String type(String s) {
        return jedis.type(s);
    }

    public Long expire(String s, int i) {
        return jedis.expire(s, i);
    }

    public Long expireAt(String s, long l) {
        return jedis.expireAt(s, l);
    }

    public Long ttl(String s) {
        return jedis.ttl(s);
    }

    public boolean setbit(String s, long l, boolean b) {
        return jedis.setbit(s, l, b);
    }

    public boolean getbit(String s, long l) {
        return jedis.getbit(s, l);
    }

    public long setrange(String s, long l, String s1) {
        return jedis.setrange(s, l, s1);
    }

    public String getrange(String s, long l, long l1) {
        return jedis.getrange(s, l, l1);
    }

    public String getSet(String s, String s1) {
        return jedis.getSet(s, s1);
    }

    public Long setnx(String s, String s1) {
        return jedis.setnx(s, s1);
    }

    public String setex(String s, int i, String s1) {
        return jedis.setex(s, i, s1);
    }

    public Long decrBy(String s, long l) {
        return jedis.decrBy(s, l);
    }

    public Long decr(String s) {
        return jedis.decr(s);
    }

    public Long incrBy(String s, long l) {
        return jedis.incrBy(s, l);
    }

    public Long incr(String s) {
        return jedis.incr(s);
    }

    public Long append(String s, String s1) {
        return jedis.append(s, s1);
    }

    public String substr(String s, int i, int i1) {
        return jedis.substr(s, i, i1);
    }

    public Long hset(String s, String s1, String s2) {
        return jedis.hset(s, s1, s2);
    }

    public String hget(String s, String s1) {
        return jedis.hget(s, s1);
    }

    public Long hsetnx(String s, String s1, String s2) {
        return jedis.hsetnx(s, s1, s2);
    }

    public String hmset(String s, Map<String, String> stringStringMap) {
        return jedis.hmset(s, stringStringMap);
    }

    public List<String> hmget(String s, String... strings) {
        return jedis.hmget(s, strings);
    }

    public Long hincrBy(String s, String s1, long l) {
        return jedis.hincrBy(s, s1, l);
    }

    public Boolean hexists(String s, String s1) {
        return jedis.hexists(s, s1);
    }

    public Long hdel(String s, String s1) {
        return jedis.hdel(s, s1);
    }

    public Long hlen(String s) {
        return jedis.hlen(s);
    }

    public Set<String> hkeys(String s) {
        return jedis.hkeys(s);
    }

    public List<String> hvals(String s) {
        return jedis.hvals(s);
    }

    public Map<String, String> hgetAll(String s) {
        return jedis.hgetAll(s);
    }

    public Long rpush(String s, String s1) {
        return jedis.rpush(s, s1);
    }

    public Long lpush(String s, String s1) {
        return jedis.lpush(s, s1);
    }

    public Long llen(String s) {
        return jedis.llen(s);
    }

    public List<String> lrange(String s, long l, long l1) {
        return jedis.lrange(s, l, l1);
    }

    public String ltrim(String s, long l, long l1) {
        return jedis.ltrim(s, l, l1);
    }

    public String lindex(String s, long l) {
        return jedis.lindex(s, l);
    }

    public String lset(String s, long l, String s1) {
        return jedis.lset(s, l, s1);
    }

    public Long lrem(String s, long l, String s1) {
        return jedis.lrem(s, l, s1);
    }

    public String lpop(String s) {
        return jedis.lpop(s);
    }

    public String rpop(String s) {
        return jedis.rpop(s);
    }

    public Long sadd(String s, String s1) {
        return jedis.sadd(s, s1);
    }

    public Set<String> smembers(String s) {
        return jedis.smembers(s);
    }

    public Long srem(String s, String s1) {
        return jedis.srem(s, s1);
    }

    public String spop(String s) {
        return jedis.spop(s);
    }

    public Long scard(String s) {
        return jedis.scard(s);
    }

    public Boolean sismember(String s, String s1) {
        return jedis.sismember(s, s1);
    }

    public String srandmember(String s) {
        return jedis.srandmember(s);
    }

    public Long zadd(String s, double v, String s1) {
        return jedis.zadd(s, v, s1);
    }

    public Set<String> zrange(String s, int i, int i1) {
        return jedis.zrange(s, i, i1);
    }

    public Long zrem(String s, String s1) {
        return jedis.zrem(s, s1);
    }

    public Double zincrby(String s, double v, String s1) {
        return jedis.zincrby(s, v, s1);
    }

    public Long zrank(String s, String s1) {
        return jedis.zrank(s, s1);
    }

    public Long zrevrank(String s, String s1) {
        return jedis.zrevrank(s, s1);
    }

    public Set<String> zrevrange(String s, int i, int i1) {
        return jedis.zrevrange(s, i, i1);
    }

    public Set<Tuple> zrangeWithScores(String s, int i, int i1) {
        return jedis.zrangeWithScores(s, i, i1);
    }

    public Set<Tuple> zrevrangeWithScores(String s, int i, int i1) {
        return jedis.zrevrangeWithScores(s, i, i1);
    }

    public Long zcard(String s) {
        return jedis.zcard(s);
    }

    public Double zscore(String s, String s1) {
        return jedis.zscore(s, s1);
    }

    public List<String> sort(String s) {
        return jedis.sort(s);
    }

    public List<String> sort(String s, SortingParams sortingParams) {
        return jedis.sort(s, sortingParams);
    }

    public Long zcount(String s, double v, double v1) {
        return jedis.zcount(s, v, v1);
    }

    public Set<String> zrangeByScore(String s, double v, double v1) {
        return jedis.zrangeByScore(s, v, v1);
    }

    public Set<String> zrevrangeByScore(String s, double v, double v1) {
        return jedis.zrevrangeByScore(s, v, v1);
    }

    public Set<String> zrangeByScore(String s, double v, double v1, int i, int i1) {
        return jedis.zrangeByScore(s, v, v1, i, i1);
    }

    public Set<String> zrevrangeByScore(String s, double v, double v1, int i, int i1) {
        return jedis.zrevrangeByScore(s, v, v1, i, i1);
    }

    public Set<Tuple> zrangeByScoreWithScores(String s, double v, double v1) {
        return jedis.zrangeByScoreWithScores(s, v, v1);
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String s, double v, double v1) {
        return jedis.zrevrangeByScoreWithScores(s, v, v1);
    }

    public Set<Tuple> zrangeByScoreWithScores(String s, double v, double v1, int i, int i1) {
        return jedis.zrangeByScoreWithScores(s, v, v1, i, i1);
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String s, double v, double v1, int i, int i1) {
        return jedis.zrevrangeByScoreWithScores(s, v, v1, i, i1);
    }

    public Long zremrangeByRank(String s, int i, int i1) {
        return jedis.zremrangeByRank(s, i, i1);
    }

    public Long zremrangeByScore(String s, double v, double v1) {
        return jedis.zremrangeByScore(s, v, v1);
    }

    public Long linsert(String s, BinaryClient.LIST_POSITION list_position, String s1, String s2) {
        return jedis.linsert(s, list_position, s1, s2);
    }

    public String auth(String password) {
        return jedis.auth(password);
    }

    public void subscribe(PubSub jedisPubSub, String... channels) {
        jedis.subscribe(jedisPubSub.getJedisPubSub(), channels);
    }

    public void psubscribe(PubSub jedisPubSub, String... patterns) {
        jedis.psubscribe(jedisPubSub.getJedisPubSub(), patterns);
    }

    public Pipeline pipelined() {
        return jedis.pipelined();
    }

}
