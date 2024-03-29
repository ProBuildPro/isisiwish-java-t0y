package top.cfish.algorithm.map;

import top.cfish.algorithm.inface.IMap;
import top.cfish.algorithm.tree.bstree.AVLTree;

/**
 * @author: isisiwish
 * @date: 2018/9/10
 * @time: 15:06
 */
public class AVLMap<K extends Comparable<K>, V> implements IMap<K, V>
{
    private AVLTree<K, V> avl;
    
    public AVLMap()
    {
        avl = new AVLTree<>();
    }
    
    @Override
    public int getSize()
    {
        return avl.getSize();
    }
    
    @Override
    public boolean isEmpty()
    {
        return avl.isEmpty();
    }
    
    @Override
    public void add(K key, V value)
    {
        avl.add(key, value);
    }
    
    @Override
    public boolean contains(K key)
    {
        return avl.contains(key);
    }
    
    @Override
    public V get(K key)
    {
        return avl.get(key);
    }
    
    @Override
    public void set(K key, V newValue)
    {
        avl.set(key, newValue);
    }
    
    @Override
    public V remove(K key)
    {
        return avl.remove(key);
    }
}
