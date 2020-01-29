package dream.consult.comp.time.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.consult.comp.time.dao.MaLineTimeListDAO;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;

/**
 * 가동시간설정 - 목록 
 * @author  kim21017
 * @version $Id: MaLineTimeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maLineTimeListDAOTarget"
 * @spring.txbn id="maLineTimeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaLineTimeListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaLineTimeListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaLineTimeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeCommonDTO
     * @return List
     */
    public List findEqLocList(MaLineTimeCommonDTO maLineTimeCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT        																													");
        query.append("    ''          																								seqNo     			");
        query.append("    ,''         																								isDelCheck        	");
        query.append("    ,lnwrklist_id           																					lnWrkListId       	");
        query.append("    ,comp_no             																						compNo       		");
        query.append("    ,(SELECT description FROM TACOMP WHERE comp_no = a.comp_no)												compDesc			");
        query.append("    ,lnwrklist_no           																					lnWrkListNo       	");
        query.append("    ,description            																					description       	");
        query.append("    ,(SELECT description FROM TAEQLOC WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id)					eqlocDesc    		");
        query.append("    ,(SELECT description FROM TAWRKCALLIST WHERE comp_no = a.comp_no AND wrkcallist_id = a.wrkcallist_id) 	wrkCalListDesc      ");
        query.append("    ,(SELECT description FROM TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant)      					plantDesc     		");
        query.append("    ,equip_id																				 					equipNo  			");
        query.append("    ,(select description from TAEQUIPMENT WHERE comp_no = a.comp_no AND equip_id = a.equip_id) 				equipNameDesc		");
        query.append("    ,dbo.SFACODE_TO_DESC(a.lnwrk_create_type,'LNWRK_CREATE_TYPE','SYS','','ko')    							runTimeSettingDesc	");
        query.append("    ,a.REMARK               																					REMARK        		");
        query.append("FROM TALNWRKLIST a        																										");
        query.append("WHERE  1 = 1              																										");
        query.append(this.getWhere(maLineTimeCommonDTO,user));
        query.getOrderByQuery("a.lnwrklist_id","a.description", maLineTimeCommonDTO.getOrderBy(), maLineTimeCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maLineTimeCommonDTO.getIsLoadMaxCount(), maLineTimeCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaLineTimeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaLineTimeCommonDTO maLineTimeCommonDTO, User loginUser)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        if (!"".equals(maLineTimeCommonDTO.getLnWrkListId()))
        {
            query.getAndQuery("a.lnwrklist_id", maLineTimeCommonDTO.getLnWrkListId());
            return query.toString();
        }
        
        if(!"".equals(maLineTimeCommonDTO.getCompNo())) {
            query.getAndQuery("a.comp_no", maLineTimeCommonDTO.getCompNo());
        }
        else {
            query.append("AND a.comp_no IN(SELECT comp_no FROM TACOMP       ");
            query.append("                            WHERE 1=1     ");
            query.getLikeQuery("description", maLineTimeCommonDTO.getFilterCompDesc());
            query.append("                            )     ");
        }
        
        query.getLikeQuery("a.description", maLineTimeCommonDTO.getFilterLnWrkListDesc());
        
        return query.toString();
    }

    @Override
    public String findTotalCount(MaLineTimeCommonDTO maLineTimeCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                    ");
        query.append("      count(1)            ");
        query.append("FROM   TALNWRKLIST a      ");
        query.append("WHERE  1 = 1              ");
        query.append(this.getWhere(maLineTimeCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        String lnWrkListId=id;
        
        query.append("DELETE FROM TALNWRKLIST                         ");
        query.append("WHERE  lnwrklist_id     = '"+lnWrkListId+"'   ");
        query.append("  AND  comp_no            = '"+compNo+"'          ");
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
}