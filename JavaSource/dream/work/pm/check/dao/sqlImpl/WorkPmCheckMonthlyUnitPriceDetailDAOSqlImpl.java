package dream.work.pm.check.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.pm.check.dao.WorkPmCheckMonthlyUnitPriceDetailDAO;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceDetailDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceListDTO;

/**
 * 월별단가 - Detail DAO implements
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceDetailDAOSqlImpl.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmCheckMonthlyUnitPriceDetailDAOTarget"
 * @spring.txbn id="workPmCheckMonthlyUnitPriceDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmCheckMonthlyUnitPriceDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmCheckMonthlyUnitPriceDetailDAO
{
	
    public WorkPmCheckMonthlyUnitPriceDetailDTO findDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO, WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT                                                    ");
        query.append("     x.stdchkpointprice_id        stdChkPointPriceId      ");
        query.append("   , x.std_check_point_id         stdCheckPointId         ");
        query.append("   , x.yyyymm                     yyyymm                  ");
        query.append("   , x.unit_price                 unitPrice               ");
        query.append("   , x.REMARK                     remark                  ");
        query.append(" FROM TASTDCHKPOINTPRICE x                                ");
        query.append("WHERE  1=1                                                ");
        query.append("AND  x.comp_no                = ?                         ");
        query.append("AND  x.stdchkpointprice_id    = ?                         ");
        query.append("AND  x.std_check_point_id     = ?                         ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
               , workPmCheckMonthlyUnitPriceListDTO.getStdChkPointPriceId()
               , workPmCheckCommonDTO.getCheckPointId()
        };
    
        WorkPmCheckMonthlyUnitPriceDetailDTO workPmCheckMonthlyUnitPriceDetailDTO = 
        		(WorkPmCheckMonthlyUnitPriceDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkPmCheckMonthlyUnitPriceDetailDTO()));
        
        return workPmCheckMonthlyUnitPriceDetailDTO;
        
    }

    public int insertDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO,WorkPmCheckMonthlyUnitPriceDetailDTO workPmCheckMonthlyUnitPriceDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TASTDCHKPOINTPRICE (     ");
        query.append("  comp_no                            ");
        query.append(", stdchkpointprice_id                ");
        query.append(", std_check_point_id                 ");
        query.append(", yyyymm                             ");
        query.append(", unit_price                         ");
        query.append(", remark                             ");
        query.append(") VALUES (                           ");
        query.append("?                                    ");
        query.append(", ?                                  ");
        query.append(", ?                                  ");
        query.append(", ?                                  ");
        query.append(", ?                                  ");
        query.append(", ?                                  ");
        query.append(")                                    ");

        Object[] objects = new Object[] {
                 user.getCompNo()
                 , workPmCheckMonthlyUnitPriceDetailDTO.getStdChkPointPriceId()
                 , workPmCheckMonthlyUnitPriceDetailDTO.getCheckPointId()
                 , workPmCheckMonthlyUnitPriceDetailDTO.getYyyymm().replaceAll("-", "")
                 , workPmCheckMonthlyUnitPriceDetailDTO.getUnitPrice().replaceAll(",", "")
                 , workPmCheckMonthlyUnitPriceDetailDTO.getRemark()
        };
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO,WorkPmCheckMonthlyUnitPriceDetailDTO workPmCheckMonthlyUnitPriceDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TASTDCHKPOINTPRICE SET                 ");
        query.append("   yyyymm                     = ?             ");
        query.append("  ,unit_price                 = ?             ");
        query.append("  ,remark                     = ?             ");
        query.append("WHERE  comp_no                = ?             ");
        query.append("  AND  stdchkpointprice_id    = ?             ");
        query.append("  AND  std_check_point_id     = ?             ");
        
        Object[] objects = new Object[] {
                workPmCheckMonthlyUnitPriceDetailDTO.getYyyymm().replaceAll("-", "")
                , workPmCheckMonthlyUnitPriceDetailDTO.getUnitPrice().replaceAll(",", "")
                , workPmCheckMonthlyUnitPriceDetailDTO.getRemark()
                , user.getCompNo()
                , workPmCheckMonthlyUnitPriceDetailDTO.getStdChkPointPriceId()
                , workPmCheckMonthlyUnitPriceDetailDTO.getCheckPointId()
        };
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}