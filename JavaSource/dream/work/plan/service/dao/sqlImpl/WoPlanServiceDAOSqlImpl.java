package dream.work.plan.service.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.plan.service.dao.WoPlanServiceDAO;
import dream.work.plan.service.dto.WoPlanServiceDTO;

/**
 * 서비스작업 DAOSqlImpl 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="woPlanServiceDAOTarget"
 * @spring.txbn id="woPlanServiceDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WoPlanServiceDAOSqlImpl extends BaseJdbcDaoSupportSql implements WoPlanServiceDAO
{
	public List findList(WoPlanServiceDTO woPlanServiceDTO, User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append(getColums(woPlanServiceDTO, user));
        query.append(getTables(woPlanServiceDTO, user));
        query.append(this.getWhere(woPlanServiceDTO, user));
        query.append(getOrderBy(woPlanServiceDTO, user));
        
		return getJdbcTemplate().queryForList(query.toString(woPlanServiceDTO.getIsLoadMaxCount(), woPlanServiceDTO.getFirstRow()));
	}

	public String findTotalCount(WoPlanServiceDTO woPlanServiceDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(woPlanServiceDTO, user));
        query.append(this.getWhere(woPlanServiceDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
	
	@Override
	public int[] insertDetail(final List<WoPlanServiceDTO> list, final User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("INSERT INTO TAWOPLANSERVICE (                       					");
        query.append("   	 comp_no       	, woplanservice_id			, wkor_id			");
        query.append("     , ord_no			, description				, service_id		");
        query.append("     , contract_id	, vendor_id					, plan_work_time	");
        query.append("     , service_uom	, unit_price 				, amount			");
        query.append("     , remark 														");
        query.append(")VALUES(                                      						");
        query.append("  	 ?              , ?              			, ?         		");
        query.append("     , ?              , ?              			, ?         		");
        query.append("     , ?              , ?                     	, ?  				");
        query.append("     , ?              , ?                     	, ?  				");
        query.append("     , ?              								      			");
        query.append(")                                             						");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	WoPlanServiceDTO woPlanServiceDTO = list.get(i);
                
                Object[] objects = new Object[] {
                		 user.getCompNo()
                	   , woPlanServiceDTO.getWoPlanServiceId()
                	   , woPlanServiceDTO.getWkOrId()
                	   , woPlanServiceDTO.getOrdNo()
                	   , woPlanServiceDTO.getWoDesc()
                	   , woPlanServiceDTO.getServiceId()
                	   , woPlanServiceDTO.getContractId()
                	   , woPlanServiceDTO.getVendorId()
                	   , CommonUtil.getRowMoneyToNum(woPlanServiceDTO.getPlanWorkTime())
                	   , woPlanServiceDTO.getUomId()
                	   , CommonUtil.getRowMoneyToNum(woPlanServiceDTO.getUnitPrice()) 
                	   , CommonUtil.getRowMoneyToNum(woPlanServiceDTO.getAmt())
                	   , woPlanServiceDTO.getRemark()
                };
                
                for(int j=0;j<objects.length;j++){
                	 ps.setObject(j+1, getObject(objects[j]));
                }
            }
            
            @Override
            public int getBatchSize()
            {
                return list.size();
            }
        });
	}
	
	public int[] updateDetail(final List<WoPlanServiceDTO> list, final User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("UPDATE TAWOPLANSERVICE SET			");
		query.append("	     ord_no				= ?			");
		query.append("	   , description		= ?			");
		query.append("	   , service_id			= ?			");
		query.append("	   , contract_id		= ?			");
		query.append("	   , vendor_id			= ?			");
		query.append("	   , plan_work_time		= ?			");
		query.append("	   , service_uom		= ?			");
    	query.append("	   , unit_price			= ?			");
    	query.append("	   , amount				= ?			");
    	query.append("	   , remark				= ?			");
    	query.append(" WHERE comp_no			= ?			");
    	query.append("   AND woplanservice_id	= ?			");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				WoPlanServiceDTO woPlanServiceDTO = list.get(i);
				
				Object[] objects = new Object[] {
					     woPlanServiceDTO.getOrdNo()
					   , woPlanServiceDTO.getWoDesc()
					   , woPlanServiceDTO.getServiceId()
					   , woPlanServiceDTO.getContractId()
					   , woPlanServiceDTO.getVendorId()
					   , CommonUtil.getRowMoneyToNum(woPlanServiceDTO.getPlanWorkTime())
					   , woPlanServiceDTO.getUomId()
					   , CommonUtil.getRowMoneyToNum(woPlanServiceDTO.getUnitPrice())
					   , CommonUtil.getRowMoneyToNum(woPlanServiceDTO.getAmt())
					   , woPlanServiceDTO.getRemark()
					   , user.getCompNo()
                       , woPlanServiceDTO.getWoPlanServiceId()                                                                     
                };
				
				for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
			}
    		
			@Override
			public int getBatchSize() {
				return list.size();
			}
    	});
	}
	
	public int[] deleteList(final List<WoPlanServiceDTO> list, final User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("DELETE TAWOPLANSERVICE				");
    	query.append(" WHERE comp_no			= ?			");
    	query.append("   AND woplanservice_id	= ?			");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				WoPlanServiceDTO woPlanServiceDTO = list.get(i);
				
				Object[] objects = new Object[] {
					     user.getCompNo()
                       , woPlanServiceDTO.getWoPlanServiceId()                                                                     
                };
				
				for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
			}
    		
			@Override
			public int getBatchSize() {
				return list.size();
			}
    	});
	}

	@Override
	public String getColums(WoPlanServiceDTO woPlanServiceDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT																									");
		query.append("       ''                                        										AS ISDELCHECK		");
		query.append("     , x.ord_no                                										AS ORDNO			");
		query.append("     , x.woplanservice_id                         									AS WOPLANSERVICEID	");
		query.append("     , x.description                            										AS WODESC			");
		query.append("     , x.service_id                            										AS SERVICEID		");
		query.append("     , (SELECT service_no 																				");
		query.append("       	FROM TASERVICE aa																				");
		query.append("         WHERE aa.comp_no = x.comp_no																		");
		query.append("           AND aa.service_id = x.service_id)											AS SERVICENO		");
		query.append("     , (SELECT description																				");
		query.append("       	FROM TASERVICE aa																				");
		query.append("         WHERE aa.comp_no = x.comp_no																		");
		query.append("           AND aa.service_id = x.service_id)											AS SERVICEDESC		");
		query.append("     , x.contract_id                            										AS CONTRACTID		");
		query.append("     , (SELECT contract_no																				");
		query.append("       	FROM TACONTRACT aa																				");
		query.append("         WHERE aa.comp_no = x.comp_no																		");
		query.append("           AND aa.contract_id = x.contract_id)										AS CONTRACTNO		");
		query.append("     , (SELECT description																				");
		query.append("       	FROM TACONTRACT aa																				");
		query.append("         WHERE aa.comp_no = x.comp_no																		");
		query.append("           AND aa.contract_id = x.contract_id)    									AS CONTRACTDESC		");
		query.append("     , x.vendor_id                            										AS VENDORID			");
		query.append("     , (SELECT vendor_no																					");
		query.append("       	FROM TAVENDOR aa																				");
		query.append("         WHERE aa.comp_no = x.comp_no																		");
		query.append("           AND aa.vendor_id = x.vendor_id)        									AS VENDORNO			");
		query.append("     , (SELECT description																				");
		query.append("       	FROM TAVENDOR aa																				");
		query.append("         WHERE aa.comp_no = x.comp_no																		");
		query.append("           AND aa.vendor_id = x.vendor_id)        									AS VENDORDESC		");
		query.append("     , x.plan_work_time                           									AS PLANWORKTIME 	");
		query.append("     , x.service_uom                                    												AS UOMID			");
		query.append("     , dbo.SFACODE_TO_DESC(x.service_uom, 'SERVICE_UOM', 'SYS', x.comp_no,'"+ user.getLangId() +"')	AS UOMDESC			");
		query.append("     , x.unit_price                            										AS UNITPRICE		");
		query.append("     , x.amount                                										AS AMT				");
		query.append("     , x.remark                                										AS REMARK			");
	
		return query.toString();
	}

	@Override
	public String getTables(WoPlanServiceDTO woPlanServiceDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("  FROM TAWOPLANSERVICE x												");

		return query.toString();
	}

	@Override
	public String getOrderBy(WoPlanServiceDTO woPlanServiceDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getOrderByQuery("x.woplanservice_id", "x.ord_no DESC", woPlanServiceDTO.getOrderBy(), woPlanServiceDTO.getDirection());		
		
		return query.toString();
	}

	@Override
	public String getWhere(WoPlanServiceDTO woPlanServiceDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append(" WHERE 1 = 1															");
		
		query.getAndQuery("x.wkor_id", woPlanServiceDTO.getWkOrId());
		
		if(!"".equals(woPlanServiceDTO.getWoPlanServiceId())){
            query.getAndQuery("x.woplanservice_id", woPlanServiceDTO.getWoPlanServiceId());
            
            return query.toString();
        }
		
		// 회사
		query.getAndQuery("x.comp_no", user.getCompNo());
		       
        return query.toString();
	}
}