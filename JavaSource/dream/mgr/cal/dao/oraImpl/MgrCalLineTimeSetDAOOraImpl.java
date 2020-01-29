package dream.mgr.cal.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.cal.dao.MgrCalLineTimeSetDAO;
import dream.mgr.cal.dto.MgrCalLineTimeSetDTO;

/**
 * 가동달력설정 - 목록 
 * @author  euna0207
 * @version $Id: MgrCalLineTimeSetDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="mgrCalLineTimeSetDAOTarget"
 * @spring.txbn id="mgrCalLineTimeSetDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCalLineTimeSetDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrCalLineTimeSetDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalLineTimeSetDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrCalLineTimeSetDTO
     * @return List
     */
    public List findList(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(getColums(mgrCalLineTimeSetDTO, user));
        query.append(getTables(mgrCalLineTimeSetDTO, user));
        query.append(this.getWhere(mgrCalLineTimeSetDTO,user));
        query.append(getOrderBy(mgrCalLineTimeSetDTO, user));
        
        return getJdbcTemplate().queryForList(query.toString(mgrCalLineTimeSetDTO.getIsLoadMaxCount(), mgrCalLineTimeSetDTO.getFirstRow()));
    }
    

	@Override
	public String getColums(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user) {
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
	public String getTables(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
        query.append("FROM TALNWRKLIST a        						");

		return query.toString();
	}

	@Override
	public String getOrderBy(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
        query.getOrderByQuery("a.description", mgrCalLineTimeSetDTO.getOrderBy(), mgrCalLineTimeSetDTO.getDirection());

		return query.toString();
	}
    
    /**
     * Filter 조건
     * @author  euna0207
     * @version $Id: MgrCalLineTimeSetDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrCalLineTimeSetDTO
     * @return
     * @throws Exception
     */
    public String getWhere(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.append("WHERE  1 = 1             		 ");
        query.getAndQuery("a.lnwrk_calendar_type", "R");
        
        if (!"".equals(mgrCalLineTimeSetDTO.getLnWrkListId()))
        {
            query.getAndQuery("a.lnwrklist_id", mgrCalLineTimeSetDTO.getLnWrkListId());
            return query.toString();
        }
        
        if(!"".equals(loginUser.getCompNo())) {
            query.getAndQuery("a.comp_no", loginUser.getCompNo());
        }
        
        query.getLikeQuery("a.description", mgrCalLineTimeSetDTO.getFilterLnWrkListDesc());
        
        return query.toString();
    }

    @Override
    public String findTotalCount(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("      count(1)            ");
        query.append(getTables(mgrCalLineTimeSetDTO, user));
        query.append(this.getWhere(mgrCalLineTimeSetDTO,user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    /**
     * delete
     * @author euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] deleteList(final List<MgrCalLineTimeSetDTO> list, final User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TALNWRKLIST           ");
        query.append("WHERE  lnwrklist_id    	= ?   	");
        query.append("  AND  comp_no            = ?     ");

    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO = list.get(i);
				
				Object[] objects = new Object[] {   
						mgrCalLineTimeSetDTO.getLnWrkListId()
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
     * @author euna0207
     * @version $Id: MgrCalLineTimeSetDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrCalLineTimeSetDTO
     * @param user
     * @return
     */
    @Override
    public int[] insertDetail(final List<MgrCalLineTimeSetDTO> list, final User user) throws Exception
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
            	MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO = list.get(i);
                
            	Object[] objects = new Object[] {
        		user.getCompNo()
                ,mgrCalLineTimeSetDTO.getLnWrkListId()
                ,mgrCalLineTimeSetDTO.getLnWrkListNo()
                ,mgrCalLineTimeSetDTO.getEqLocId()
                ,mgrCalLineTimeSetDTO.getLnWrkListDesc()
                ,mgrCalLineTimeSetDTO.getWrkCalListId()
                ,mgrCalLineTimeSetDTO.getIsUse()
                ,mgrCalLineTimeSetDTO.getRemark()
                ,mgrCalLineTimeSetDTO.getPlantId()
                ,mgrCalLineTimeSetDTO.getEquipNameId()
                ,mgrCalLineTimeSetDTO.getRunTimeSettingId()
                ,"R"
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
     * @author euna0207
     * @version $Id: MgrCalLineTimeSetDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrCalLineTimeSetDTO
     * @return
     */
    public int[] updateDetail(final List<MgrCalLineTimeSetDTO> list, final User user) throws Exception
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
				MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO = list.get(i);
		
				Object[] objects = new Object[] {
	    			user.getCompNo()
	                ,mgrCalLineTimeSetDTO.getEqLocId()
	                ,mgrCalLineTimeSetDTO.getLnWrkListDesc()
	                ,mgrCalLineTimeSetDTO.getWrkCalListId()
	                ,mgrCalLineTimeSetDTO.getIsUse()
	                ,mgrCalLineTimeSetDTO.getRemark()
	                ,mgrCalLineTimeSetDTO.getPlantId()
	                ,mgrCalLineTimeSetDTO.getLnWrkListNo()
	                ,mgrCalLineTimeSetDTO.getEquipNameId()
	                ,mgrCalLineTimeSetDTO.getRunTimeSettingId()
	                ,mgrCalLineTimeSetDTO.getLnWrkListId()
	                ,mgrCalLineTimeSetDTO.getCompNo()
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