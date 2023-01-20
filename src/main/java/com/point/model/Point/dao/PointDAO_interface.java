package com.point.model.Point.dao;

import com.point.model.Point.pojo.Point;
import java.util.List;

public interface PointDAO_interface {

	public void insert(Point point);

	public void update(Point point);

	public void delete(Integer pointid);
	
	Point getByPK(Integer pointid);
	
    public List<Point> getAll();

}