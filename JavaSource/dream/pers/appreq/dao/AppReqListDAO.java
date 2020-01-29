package dream.pers.appreq.dao;

import java.util.List;

import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * 예방점검 - 목록
 * @author  javaworker
 * @version $Id: AppReqListDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
 * @since   1.0
 */
public interface AppReqListDAO extends BaseJdbcDaoSupportIntf
{
    public List findAppReqList(AppReqCommonDTO appReqCommonDTO);
    
    public int findAppReqListCount(AppReqCommonDTO appReqCommonDTO);
}