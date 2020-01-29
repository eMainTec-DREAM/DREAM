package dream.mgr.contract.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.google.gson.Gson;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.mgr.contract.dao.MgrContractDAO;
import dream.mgr.contract.dto.MgrContractDTO;

/**
 * 단가계약설정 - 목록 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrContractDAOTarget"
 * @spring.txbn id="mgrContractDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrContractDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrContractDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractDTO
     * @return List
     */
    public List findList(MgrContractDTO mgrContractDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(getColums(mgrContractDTO, user));
        query.append(getTables(mgrContractDTO, user));
        query.append(this.getWhere(mgrContractDTO,user));
        query.append(getOrderBy(mgrContractDTO, user));
        
        return getJdbcTemplate().queryForList(query.toString(mgrContractDTO.getIsLoadMaxCount(), mgrContractDTO.getFirstRow()));
    }

	@Override
	public String getColums(MgrContractDTO mgrContractDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 																									");
		query.append("    '' 																					seqNo			");
		query.append("    , '' 																					isDelCheck		");
		query.append("    , x.contract_id 																		contractId		");
		query.append("    , x.contract_no 																		contractNo		");
		query.append("    , x.description 																		contractDesc	");
		query.append("	  , x.vendor_id																			vendorId		");
		query.append("    , (SELECT a.vendor_no FROM TAVENDOR a 																");
		query.append("        WHERE a.comp_no = x.comp_no 																		");
		query.append("             AND a.vendor_id = x.vendor_id) 												vendorNo		");
		query.append("    , (SELECT a.description FROM TAVENDOR a 																");
		query.append("        WHERE a.comp_no = x.comp_no 																		");
		query.append("             AND a.vendor_id = x.vendor_id) 												vendorDesc		");
		query.append("    , x.contract_date 																	contractDate	");
		query.append("    , CASE WHEN x.start_date IS NOT NULL THEN 																		");
		query.append("        ( CASE WHEN x.end_date IS NOT NULL THEN 																		");
		query.append("              substring(x.start_date,0,5) + '-' + substring(x.start_date,5,2) + '-' + substring(x.start_date,7,2)		");
		query.append("                  + ' ~ ' + 																							");
		query.append("                      substring(x.end_date,0,5) + '-' + substring(x.end_date,5,2) + '-' + substring(x.end_date,7,2)	");
		query.append("          ELSE 																										");
		query.append("              substring(x.start_date,0,5) + '-' + substring(x.start_date,5,2) + '-' + substring(x.start_date,7,2)		");
		query.append("                  + ' ~ ' 																							");
		query.append("          END )																										");
		query.append("      ELSE 																											");
		query.append("        ( CASE WHEN x.end_date IS NOT NULL THEN 																		");
		query.append("              ' ~ ' + substring(x.end_date,0,5) + '-' + substring(x.end_date,5,2) + '-' + substring(x.end_date,7,2)	");
		query.append("          ELSE ''																									");
		query.append("           END )																									");
		query.append("      END             																	contractPeriod          ");
		query.append("    , x.start_date 																		contractStartDate		");
		query.append("    , x.end_date 																			contractEndDate			");
		query.append("    , x.tot_amt 																			contractAmount	");
		query.append("    , (SELECT dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getCompNo()+"') ) 	isUse			");
		query.append("    , x.remark 																			remark			");

		return query.toString();
	}

	@Override
	public String getTables(MgrContractDTO mgrContractDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("FROM TACONTRACT x		");

		return query.toString();
	}

	@Override
	public String getOrderBy(MgrContractDTO mgrContractDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
        query.getOrderByQuery("x.contract_no", mgrContractDTO.getOrderBy(), mgrContractDTO.getDirection());

		return query.toString();
	}
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractDTO
     * @return
     * @throws Exception
     */
    public String getWhere(MgrContractDTO mgrContractDTO, User loginUser)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("WHERE  1 = 1             		 ");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        
        if (!"".equals(mgrContractDTO.getContractId()))
        {
            query.getAndQuery("x.contract_id", mgrContractDTO.getContractId());
            return query.toString();
        }
        
        //계약#
        query.getLikeQuery("x.contract_no", mgrContractDTO.getFilterContractNo());
        
        //계약명
        query.getLikeQuery("x.description", mgrContractDTO.getFilterContractDesc());
        
        //업체명
        query.getCodeLikeQuery("x.vendor_id", "(SELECT a.description FROM  TAVENDOR a WHERE a.comp_no = x.comp_no AND a.vendor_id = x.vendor_id )", 
        		mgrContractDTO.getFilterVendorId(), mgrContractDTO.getFilterVendorDesc());
        
        //서비스명
        if(!"".equals(mgrContractDTO.getFilterServiceDesc()) || !"".equals(mgrContractDTO.getFilterServiceId()))
		{
        	query.append("  AND EXISTS (SELECT 1 FROM TACONTRACTITEM a INNER JOIN TASERVICE b 		");
        	query.append("                      ON a.comp_no = b.comp_no AND a.service_id = b.service_id 		");
        	query.append("                      WHERE a.comp_no = x.comp_no AND a.contract_id = x.contract_id		");
        	query.getCodeLikeQuery("b.service_id", "b.description", mgrContractDTO.getFilterServiceId(), mgrContractDTO.getFilterServiceDesc());
        	query.append("                          )		");
		}
        
        //계약일자
        query.getAndDateQuery("x.contract_date", mgrContractDTO.getFilterContractStartDate(), mgrContractDTO.getFilterContractEndDate());
        
        //계약기간시작일자
        query.getAndDateQuery("x.start_date", mgrContractDTO.getFilterContractFromStartDate(), mgrContractDTO.getFilterContractFromEndDate());
        
        //계약기간종료일자
        query.getAndDateQuery("x.end_date", mgrContractDTO.getFilterContractToStartDate(), mgrContractDTO.getFilterContractToEndDate());
        
        //사용여부
        query.getSysCdQuery("x.is_use", mgrContractDTO.getFilterIsUse(), mgrContractDTO.getFilterIsUse(), "IS_USE", loginUser.getCompNo(), loginUser.getLangId());
        
        return query.toString();
    }

    @Override
    public String findTotalCount(MgrContractDTO mgrContractDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                    ");
        query.append("      count(1)            ");
        query.append(getTables(mgrContractDTO, user));
        query.append(this.getWhere(mgrContractDTO,user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

    /**
     * delete
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] deleteList(final List<MgrContractDTO> list, final User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TACONTRACT            ");
        query.append("WHERE  contract_id    	= ?   	");
        query.append("  AND  comp_no            = ?     ");

    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MgrContractDTO mgrContractDTO = list.get(i);
				
				Object[] objects = new Object[] {   
						mgrContractDTO.getContractId()
		                , user.getCompNo()
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

    /**
     * insert update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractDTO
     * @param user
     * @return
     */
    @Override
    public int[] insertDetail(final List<MgrContractDTO> list, final User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TACONTRACT (								");
        query.append("	  comp_no		, contract_id	, contract_no		");
        query.append("	, description	, vendor_id		, contract_date		");
        query.append("	, start_date	, end_date 		, tot_amt			");
        query.append("	, is_use		, remark		, cre_time			");
        query.append("	, cre_by		, upd_time		, upd_by			");
        query.append(") VALUES (											");
        query.append("	  ?             , ?             , ?					");
        query.append(" 	, ?             , ?             , ?					");
        query.append(" 	, ?             , ?             , ?					");
        query.append(" 	, ?             , ?             , ?					");
        query.append(" 	, ?             , ?             , ?					");
        query.append(")														");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	MgrContractDTO mgrContractDTO = list.get(i);
                
            	Object[] objects = new Object[] {
    	        		user.getCompNo()
    	                , mgrContractDTO.getContractId()
    	                , mgrContractDTO.getContractNo()
    	                , mgrContractDTO.getContractDesc()
    	                , mgrContractDTO.getVendorId()
    	                , CommonUtil.getRowDateToNum(mgrContractDTO.getContractDate())
    	                , CommonUtil.getRowDateToNum(mgrContractDTO.getContractStartDate())
    	                , CommonUtil.getRowDateToNum(mgrContractDTO.getContractEndDate())
    	                , CommonUtil.getRowDateToNum(mgrContractDTO.getContractAmount())
    	                , mgrContractDTO.getIsUse()
    	                , mgrContractDTO.getRemark()
    	                , mgrContractDTO.getCreTime()
    	                , user.getEmpId()
    	                , mgrContractDTO.getUpdTime()
    	                , user.getEmpId()
            };
            	
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, getObject(objects[j]));
                }
                logger.debug(new Gson().toJson(objects));
            }
            
            @Override
            public int getBatchSize()
            {
                return list.size();
            }
        });
    }
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractDTO
     * @return
     */
    public int[] updateDetail(final List<MgrContractDTO> list, final User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TACONTRACT SET			");
    	query.append("      is_use 			= ?		");
    	query.append("    , description 	= ?		");
    	query.append("    , vendor_id 		= ?		");
    	query.append("    , contract_date 	= ?		");
    	query.append("    , tot_amt 		= ? 	");
    	query.append("    , start_date 		= ?		");
    	query.append("    , end_date 		= ?		");
    	query.append("    , remark 			= ?		");
    	query.append("WHERE 1=1						");
    	query.append("  AND comp_no 		= ?		");
    	query.append("  AND contract_id 	= ?		");
    	
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MgrContractDTO mgrContractDTO = list.get(i);
		
				Object[] objects = new Object[] {
						mgrContractDTO.getIsUse()
						, mgrContractDTO.getContractDesc()
						, mgrContractDTO.getVendorId()
						, CommonUtil.getRowDateToNum(mgrContractDTO.getContractDate())
						, CommonUtil.getRowDateToNum(mgrContractDTO.getContractAmount())
						, CommonUtil.getRowDateToNum(mgrContractDTO.getContractStartDate())
						, CommonUtil.getRowDateToNum(mgrContractDTO.getContractEndDate())
						, mgrContractDTO.getRemark()
		                , user.getCompNo()
		                , mgrContractDTO.getContractId()
				};
				
				for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, getObject(objects[j]));
                }
                logger.debug(new Gson().toJson(objects));
			}
    		
			@Override
			public int getBatchSize() {
				return list.size();
			}
    	});
    }
    
}