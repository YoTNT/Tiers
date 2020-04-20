package dev.jian.daos;

import java.util.Set;

public interface CurdRepository<T>{
	T save(T t);
	Set<T> findAll();
	T findById(Integer id);
	boolean update(T t);
	boolean deleteById(Integer id);
}
