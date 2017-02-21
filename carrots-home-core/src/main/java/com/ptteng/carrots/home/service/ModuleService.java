package com.ptteng.carrots.home.service;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.ptteng.common.dao.BaseDaoService;
import com.ptteng.carrots.home.model.Module;

import org.osoa.sca.annotations.Remotable;

import java.util.List;

@Remotable
public interface ModuleService extends BaseDaoService {






	public Long insert(Module module)throws ServiceException, ServiceDaoException;



	public List<Module> insertList(List<Module> moduleList)throws ServiceException, ServiceDaoException;



	public boolean delete(Long id)throws ServiceException, ServiceDaoException;



	public boolean update(Module module)throws ServiceException, ServiceDaoException;



	public boolean updateList(List<Module> moduleList)throws ServiceException, ServiceDaoException;



	public Module getObjectById(Long id)throws ServiceException, ServiceDaoException;



	public List<Module> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;







	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer countModuleIdsByType(String type)throws ServiceException, ServiceDaoException;


	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getModuleIdsByType(String type, Integer start, Integer limit)throws ServiceException, ServiceDaoException;



	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Long getModuleIdByUrlAndType(String url, String type)throws ServiceException, ServiceDaoException;


	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getModuleIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException;

	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer countModuleIds() throws ServiceException, ServiceDaoException;


}

