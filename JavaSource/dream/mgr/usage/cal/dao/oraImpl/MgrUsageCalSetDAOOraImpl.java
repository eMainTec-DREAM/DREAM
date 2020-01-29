package dream.mgr.usage.cal.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usage.cal.dao.MgrUsageCalSetDAO;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDTO;

/**
 * 사용달력설정 - 목록 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrUsageCalSetDAOTarget"
 * @spring.txbn id="mgrUsageCalSetDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrUsageCalSetDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrUsageCalSetDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrUsageCalSetDTO
     * @return List
     */
    public List findList(MgrUsageCalSetDTO mgrUsageCalSetDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(getColums(mgrUsageCalSetDTO, user));
        query.append(getTables(mgrUsageCalSetDTO, user));
        query.append(this.getWhere(mgrUsageCalSetDTO,user));
        query.append(getOrderBy(mgrUsageCalSetDTO, user));
        
        return getJdbcTemplate().queryForList(query.toString(mgrUsageCalSetDTO.getIsLoadMaxCount(), mgrUsageCalSetDTO.getFirstRow()));
    }
    

	@Override
	public String getColums(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT        																															");
        query.append("    ''          																										seqNo     			");
        query.append("    ,''         																										isDelCheck        	");
        query.append("    ,lnwrklist_id           																							lnWrkListId       	");
        query.append("    ,comp_no             																								compNo       		");
        query.append("    ,(SELECT description FROM TACOMP WHERE comp_no = a.comp_no)  														compDesc			");
        query.append("    ,lnwrklist_no           																							lnWrkListNo       	");
        query.append("    ,description            																							description       	");
        query.append("    ,(SELECT description FROM TAEQLOC WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id)  							eqlocDesc    		");
        query.append("    ,(SELECT description FROM TAWRKCALLIST WHERE comp_no = a.comp_no AND wrkcallist_id = a.wrkcallist_id) 			wrkCalListDesc		");
        query.append("    ,(SELECT description FROM TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant)       							plantDesc     		");
        query.append("	  ,a.plant																											plantId				");
        query.append("    ,(SELECT item_no FROM TAEQUIPMENT WHERE comp_no = a.comp_no AND equip_id = a.equip_id) 							equipNo  			");
        query.append("    ,(SELECT description FROM TAEQUIPMENT WHERE comp_no = a.comp_no AND equip_id = a.equip_id) 						equipNameDesc		");
        query.append("    ,SFACODE_TO_DESC(a.lnwrk_create_type,'LNWRK_CREATE_TYPE','SYS','','"+user.getLangId()+"')    						runTimeSettingDesc	");
        query.append("    ,a.REMARK               																							REMARK        		");
        query.append("    ,a.eqloc_id                                                                                               		eqlocId         	");
        query.append("    ,a.description                                                                                            		lnWrkListDesc   	");
        query.append("    ,a.wrkcallist_id                                                                                          		wrkCalListId    	");
        query.append("    ,a.is_use                                                                                                    		isUse               ");
        query.append("    ,a.equip_id                                                                                                  		equipNameId         ");
        query.append("    ,a.lnwrk_create_type                                                                                        		runTimeSettingId    ");
        
		return query.toString();
	}

	@Override
	public String getTables(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
        query.append("FROM TALNWRKLIST a        						");

		return query.toString();
	}

	@Override
	public String getOrderBy(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
        query.getOrderByQuery("a.description", mgrUsageCalSetDTO.getOrderBy(), mgrUsageCalSetDTO.getDirection());

		return query.toString();
	}
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrUsageCalSetDTO
     * @return
     * @throws Exception
     */
    public String getWhere(MgrUsageCalSetDTO mgrUsageCalSetDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.append("WHERE  1 = 1             		 ");
        query.getAndQuery("a.lnwrk_calendar_type", "U");
        
        if (!"".equals(mgrUsageCalSetDTO.getLnWrkListId()))
        {
            query.getAndQuery("a.lnwrklist_id", mgrUsageCalSetDTO.getLnWrkListId());
            return query.toString();
        }
        
        if(!"".equals(loginUser.getCompNo())) {
            query.getAndQuery("a.comp_no", loginUser.getCompNo());
        }
        
        query.getLikeQuery("a.description", mgrUsageCalSetDTO.getFilterLnWrkListDesc());
        
        return query.toString();
    }

    @Override
    public String findTotalCount(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("      count(1)            ");
        query.append(getTables(mgrUsageCalSetDTO, user));
        query.append(this.getWhere(mgrUsageCalSetDTO,user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
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
    public int[] deleteList(final List<MgrUsageCalSetDTO> list, final User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TALNWRKLIST           ");
        query.append("WHERE  lnwrklist_id    	= ?   	");
        query.append("  AND  comp_no            = ?     ");

    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MgrUsageCalSetDTO mgrUsageCalSetDTO = list.get(i);
				
				Object[] objects = new Object[] {   
						mgrUsageCalSetDTO.getLnWrkListId()
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
     * @param mgrUsageCalSetDTO
     * @param user
     * @return
     */
    @Override
    public int[] insertDetail(final List<MgrUsageCalSetDTO> list, final User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TALNWRKLIST                       	");
        query.append(" ( comp_no		,lnwrklist_id	,lnwrklist_no	");
        query.append("  ,eqloc_id		,description	,wrkcallist_id	");
        query.append("  ,is_use			,REMARK			,plant          ");
        query.append("   ,equip_id		,lnwrk_create_type	,lnwrk_calendar_type	");
        query.append(" ) VALUES                                  		");
        query.append(" (  ?				,?				,? 		        ");
        query.append("   ,?				,?				,?          	");
        query.append("   ,?				,?				,?              ");
        query.append("   ,?				,?				,?	            ");
        query.append("  )                                           	");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	MgrUsageCalSetDTO mgrUsageCalSetDTO = list.get(i);
                
            	Object[] objects = new Object[] {
        		user.getCompNo()
                ,mgrUsageCalSetDTO.getLnWrkListId()
                ,mgrUsageCalSetDTO.getLnWrkListNo()
                ,mgrUsageCalSetDTO.getEqLocId()
                ,mgrUsageCalSetDTO.getLnWrkListDesc()
                ,mgrUsageCalSetDTO.getWrkCalListId()
                ,mgrUsageCalSetDTO.getIsUse()
                ,mgrUsageCalSetDTO.getRemark()
                ,mgrUsageCalSetDTO.getPlantId()
                ,mgrUsageCalSetDTO.getEquipNameId()
                ,mgrUsageCalSetDTO.getRunTimeSettingId()
                ,"U"
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
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrUsageCalSetDTO
     * @return
     */
    public int[] updateDetail(final List<MgrUsageCalSetDTO> list, final User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TALNWRKLIST SET       	");
        query.append("   comp_no 			= ?		");
        query.append("  ,eqloc_id    		= ?    	");
        query.append("  ,description 		= ?    	");
        query.append("  ,wrkcallist_id   	= ?    	");
        query.append(" 	,is_use   			= ?    	");
        query.append(" 	,remark   			= ?    	");
        query.append(" 	,plant 			  	= ?     ");
        query.append(" 	,lnwrklist_no	  	= ?     ");
        query.append(" 	,equip_id	  		= ?     ");
        query.append(" 	,lnwrk_create_type	= ?     ");
        query.append("WHERE lnwrklist_id 	= ?     ");
        query.append("  AND comp_no  		= ?     ");
    	
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MgrUsageCalSetDTO mgrUsageCalSetDTO = list.get(i);
		
				Object[] objects = new Object[] {
	    			user.getCompNo()
	                ,mgrUsageCalSetDTO.getEqLocId()
	                ,mgrUsageCalSetDTO.getLnWrkListDesc()
	                ,mgrUsageCalSetDTO.getWrkCalListId()
	                ,mgrUsageCalSetDTO.getIsUse()
	                ,mgrUsageCalSetDTO.getRemark()
	                ,mgrUsageCalSetDTO.getPlantId()
	                ,mgrUsageCalSetDTO.getLnWrkListNo()
	                ,mgrUsageCalSetDTO.getEquipNameId()
	                ,mgrUsageCalSetDTO.getRunTimeSettingId()
	                ,mgrUsageCalSetDTO.getLnWrkListId()
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
    
}