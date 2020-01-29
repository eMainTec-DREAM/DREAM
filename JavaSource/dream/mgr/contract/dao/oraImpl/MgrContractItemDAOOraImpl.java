package dream.mgr.contract.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.contract.dao.MgrContractItemDAO;
import dream.mgr.contract.dto.MgrContractItemDTO;

/**
 * 단가계약 설정 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrContractItemDAOTarget"
 * @spring.txbn id="mgrContractItemDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrContractItemDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrContractItemDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractItemDTO
     * @param mgrContractItemDTO
     * @param loginUser
     * @return List
     */
    public List findList(MgrContractItemDTO mgrContractItemDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(getColums(mgrContractItemDTO, loginUser));
        query.append(getTables(mgrContractItemDTO, loginUser));
        query.append(this.getWhere(mgrContractItemDTO,loginUser));
        query.append(getOrderBy(mgrContractItemDTO, loginUser));
        
        return getJdbcTemplate().queryForList(query.toString(mgrContractItemDTO.getIsLoadMaxCount(), mgrContractItemDTO.getFirstRow()));
    }
    
	@Override
	public String getColums(MgrContractItemDTO mgrContractItemDTO, User loginUser) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT														");
		query.append("    '' 									seqNo				");
		query.append("    , '' 									isDelCheck			");
		query.append("    , x.contractitem_id     				contractItemId		");
		query.append("    , x.contract_id 						contractId			");
		query.append("    , x.service_id  						serviceId			");
		query.append("    , (SELECT a.service_no 									");
		query.append("         FROM TASERVICE a 									");
		query.append("        WHERE a.comp_no= x.comp_no 							");
		query.append("          AND a.service_id = x.service_id)    serviceNo		");
		query.append("    , x.description 						contractItemDesc	");
		query.append("    , x.qty 								qty					");
		query.append("    , x.unit_price 						unitPrice			");
		query.append("    , NVL(x.amount, (qty*unit_price)) 	amount				");
		query.append("    , x.uom 								uom					");
		query.append("    , x.ord_no 							ordNo				");
		query.append("    , x.remark 							remark				");
		query.append("    , (SELECT SUM(a.amount) 									");
		query.append("         FROM TACONTRACTITEM a 								");
		query.append("        WHERE a.comp_no = x.comp_no 							");
		query.append("          AND a.contract_id = x.contract_id					");
		query.append("          AND a.contractitem_id != x.contractitem_id			");
		query.append("       )   								contractAmount		");

		return query.toString();
	}

	@Override
	public String getTables(MgrContractItemDTO mgrContractItemDTO, User user) {
		QueryBuffer query = new QueryBuffer();

		query.append("FROM TACONTRACTITEM x											");
		
		return query.toString();
	}

	@Override
	public String getOrderBy(MgrContractItemDTO mgrContractItemDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.getOrderByQuery("NVL(x.ord_no,x.contractitem_id)", mgrContractItemDTO.getOrderBy(), mgrContractItemDTO.getDirection());		
		
		return query.toString();
	}

	public String getWhere(MgrContractItemDTO mgrContractItemDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("WHERE 1 = 1	");
    	
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	query.getAndQuery("x.contract_id", mgrContractItemDTO.getContractId());
    	
    	if (!"".equals(mgrContractItemDTO.getContractItemId()))
        {
            query.getAndQuery("x.contractitem_id", mgrContractItemDTO.getContractItemId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] deleteList(final List<MgrContractItemDTO> list , final User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TACONTRACTITEM		");
    	query.append("WHERE  contractitem_id 	= ?		");
    	query.append("  AND  comp_no		 	= ?		");

    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MgrContractItemDTO mgrContractItemDTO = list.get(i);
				
		    	Object[] objects = new Object[] {
		    			mgrContractItemDTO.getContractItemId()
		    			,user.getCompNo()
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
	public String findTotalCount(MgrContractItemDTO mgrContractItemDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(mgrContractItemDTO, user));
        query.append(this.getWhere(mgrContractItemDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);

	}

	@Override
	public int[] updateDetail(final List<MgrContractItemDTO> list, final User user) throws Exception {
		QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TACONTRACTITEM SET		");
    	query.append("	service_id			= ?	    ");
    	query.append("	,ord_no				= ?	    ");
    	query.append("	,description		= ?	    ");
    	query.append("	,qty				= ?	    ");
    	query.append("	,uom				= ?		");
    	query.append("	,unit_price			= ?		");
    	query.append("	,amount				= ?		");
    	query.append("	,remark				= ?		");
    	query.append("WHERE contractitem_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	MgrContractItemDTO mgrContractItemDTO = list.get(i);

            	Object[] objects = new Object[] {
            			mgrContractItemDTO.getServiceId()
            			,mgrContractItemDTO.getOrdNo()
            			,mgrContractItemDTO.getContractItemDesc()
            			,CommonUtil.getRowMoneyToNum(mgrContractItemDTO.getQty())
            			,mgrContractItemDTO.getUom()
            			,CommonUtil.getRowMoneyToNum(mgrContractItemDTO.getUnitPrice())
            			,CommonUtil.getRowMoneyToNum(mgrContractItemDTO.getAmount())
            			,mgrContractItemDTO.getRemark()
            			,mgrContractItemDTO.getContractItemId()
            			,user.getCompNo()
            	};
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
            @Override
            public int getBatchSize()
            {
                return list.size();
            }
        });
	}

	@Override
	public int[] insertDetail(final List<MgrContractItemDTO> list, final User user) throws Exception {
		QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TACONTRACTITEM			");
    	query.append("	(comp_no		,contractitem_id	");
    	query.append("	,contract_id	,service_id			");
    	query.append("	,description	,qty				");
    	query.append("	,unit_price		,amount				");
    	query.append("	,uom			,ord_no  			");
    	query.append("	,remark								");
    	query.append("	)	VALUES							");
    	query.append("	( ?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	 ,?             ,?					");
    	query.append("	 ,?             					");
    	query.append("	)									");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException
			{
				MgrContractItemDTO mgrContractItemDTO = list.get(i);
				
				Object[] objects = new Object[] {
		    			user.getCompNo()
		    			,mgrContractItemDTO.getContractItemId()
		    			,mgrContractItemDTO.getContractId()
		    			,mgrContractItemDTO.getServiceId()
		    			,mgrContractItemDTO.getContractItemDesc()
		    			,CommonUtil.getRowMoneyToNum(mgrContractItemDTO.getQty())
		    			,CommonUtil.getRowMoneyToNum(mgrContractItemDTO.getUnitPrice())
		    			,CommonUtil.getRowMoneyToNum(mgrContractItemDTO.getAmount())
		    			,mgrContractItemDTO.getUom()
		    			,mgrContractItemDTO.getOrdNo()
		    			,mgrContractItemDTO.getRemark()
		    	};
				
				for(int j=0;j<objects.length;j++){
					ps.setObject(j+1, objects[j]);
				}
			}
			
			@Override
			public int getBatchSize()
			{
				return list.size();
			}
		});
	}
}