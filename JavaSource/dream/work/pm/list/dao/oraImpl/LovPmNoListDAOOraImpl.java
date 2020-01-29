package dream.work.pm.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.LovPmNoListDAO;
import dream.work.pm.list.dto.LovPmNoListDTO;

/**
 * 예방점검검색 팝업
 * @author  kim21017
 * @version $Id: LovPmNoListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovPmNoListDAOTarget"
 * @spring.txbn id="lovPmNoListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPmNoListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovPmNoListDAO
{
    /**
     * 예방점검 검색
     * @author  kim21017
     * @version $Id: LovPmNoListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPmNoListDTO
     * @param loginUser
     * @return
     */
    public List findPmList(LovPmNoListDTO lovPmNoListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                                             			");
        query.append("         x.pm_no                                             		AS pmNo                     ");
        query.append("        ,x.description											AS description      		");
        query.append("		  ,z.description											AS equipDesc				");
        query.append("        ,SFAIDTODESC(x.dept_id, '', 'DEPT' , '"+lovPmNoListDTO.getCompNo()+"')	AS deptDesc	");
        query.append("        ,(SELECT a.emp_name                                            						");
        query.append("          FROM   TAEMP a                                               						");
        query.append("          WHERE  a.emp_id = x.emp_id) 							AS empName                  ");
        query.append("        ,SFACODE_TO_DESC(x.pm_type, x.wo_type||'_TYPE', 'SYS', '"+lovPmNoListDTO.getCompNo()+"','"+loginUser.getLangId()+"')		AS pmTypeDesc	");
        query.append("        ,SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+lovPmNoListDTO.getCompNo()+"','"+loginUser.getLangId()+"')	AS scheduleType	");
        query.append("        ,TO_CHAR(x.cycle) || SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+lovPmNoListDTO.getCompNo()+"','"+loginUser.getLangId()+"')	AS cycleDesc	");
        query.append("        ,x.cycle 													AS cycle 					");
        query.append("        ,SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+lovPmNoListDTO.getCompNo()+"','"+loginUser.getLangId()+"') periodType,                                                                              ");
        query.append("        ,x.USAGE													AS USAGE					");
        query.append("        ,'' 			                                           	AS lastSchDate              ");
        query.append("        ,x.is_active                                              AS isActive					");
        query.append("        ,x.pm_id                                             		AS pmId                 	");
        query.append("        ,x.pm_type                                         		AS pmType                	");
        query.append("        ,x.remark                                                 As remark			        ");
        query.append("        ,(SELECT param2 FROM TACDSYSD WHERE cdsysd_no=x.pm_type AND list_type= x.wo_type||'_TYPE')	AS param	");
        query.append("FROM TAPMLST x LEFT OUTER JOIN TAPMEQUIP y 													");
		query.append("ON x.comp_no = y.comp_no                                     									");
		query.append(" AND x.pm_id = y.pm_id                      													");
		query.append("			INNER JOIN TAEQUIPMENT z															");
		query.append("          ON y.comp_no = z.comp_no															");
		query.append("           AND y.equip_id = z.equip_id														");
        query.append("WHERE   1=1											                                        ");
        query.append(this.getWhere(lovPmNoListDTO));
        query.append("ORDER by x.pm_id										                                        ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  jung7126
     * @version $Id: MaPmMstrListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param lovPmNoListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(LovPmNoListDTO lovPmNoListDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        String compNo = lovPmNoListDTO.getCompNo();

        // 삭제여부
        query.getStringEqualQuery("x.is_deleted", "N");
        query.getStringEqualQuery("y.is_deleted", "N");
        query.getStringEqualQuery("z.is_deleted", "N");
        
        if (!"".equals(lovPmNoListDTO.getPmId()))
        {
            query.getAndQuery("x.pm_id", lovPmNoListDTO.getPmId());
            return query.toString();
        }
        query.getLikeQuery("x.pm_no", lovPmNoListDTO.getPmNo());
        query.getLikeQuery("x.description", lovPmNoListDTO.getPmDesc());
        query.getDeptLevelQuery("x.dept_id", lovPmNoListDTO.getDeptId(), lovPmNoListDTO.getDeptDesc(), compNo);

        if(!"".equals(lovPmNoListDTO.getEquipId())||!"".equals(lovPmNoListDTO.getEquipDesc())){
        	query.append("AND x.pm_id IN (SELECT pm_id FROM TAPMEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("equip_id", "description||item_no", 
                    lovPmNoListDTO.getEquipId(), lovPmNoListDTO.getEquipDesc());
            query.append("									))					");
        }

        
        return query.toString();
    }
    /** 
     * 예방점검 검색 AC LOV
     * @author  kim21017
     * @version $Id: LovPmNoListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPmNoListDTO
     * @param loginUser
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findPmAcList(LovPmNoListDTO lovPmNoListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                            		                    		");
        query.append("        x.pm_no 											AS pmNo           			");
        query.append("        ,x.description                            		AS description      		");
        query.append("		  ,z.description									AS equipDesc				");
        query.append("        ,SFAIDTODESC(x.dept_id, '', 'DEPT' , '"+loginUser.getCompNo()+"')	AS deptDesc	");
        query.append("        ,(SELECT a.emp_name                       		             				");
        query.append("          FROM   TAEMP a                          		                   			");
        query.append("          WHERE  a.emp_id = x.emp_id) 					AS empName         			");
        query.append("        ,SFACODE_TO_DESC(x.pm_type, x.wo_type||'_TYPE', 'SYS', '"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"')		AS pmTypeDesc	");
        query.append("        ,SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"')	AS scheduleType	");
        query.append("        ,TO_CHAR(x.cycle) || SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"')	AS cycleDesc	");
        query.append("        ,x.cycle 											AS cycle     				");
        query.append("        ,SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"')	AS periodType	");
        query.append("        ,x.USAGE											As USAGE					");
        query.append("        ,'' 												AS lastSchDate				");
        query.append("        ,x.is_active 										AS isActive					");
        query.append("        ,x.pm_id 											AS pmId						");
        query.append("        ,x.pm_type 										AS pmType					");
        query.append("        ,x.remark 										AS remark					");
        query.append("        ,(SELECT param2 FROM TACDSYSD WHERE cdsysd_no=x.pm_type AND list_type= x.wo_type||'_TYPE')	AS param	");
        query.append("FROM TAPMLST x LEFT OUTER JOIN TAPMEQUIP y 											");
		query.append("ON x.comp_no = y.comp_no                                     							");
		query.append(" AND x.pm_id = y.pm_id                      											");
		query.append("			INNER JOIN TAEQUIPMENT z													");
		query.append("          ON y.comp_no = z.comp_no													");
		query.append("           AND y.equip_id = z.equip_id												");
        query.append("WHERE   1=1											                        		");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.pm_type", conditionMap);
        query.getLikeQuery("x.pm_no", lovPmNoListDTO.getPmNo());
        query.getLikeQuery("x.description", lovPmNoListDTO.getPmDesc());
        query.getDeptLevelQuery("x.dept_id", lovPmNoListDTO.getDeptId(), lovPmNoListDTO.getDeptDesc(), loginUser.getCompNo());

        // 삭제여부
        query.getStringEqualQuery("x.is_deleted", "N");
        query.getStringEqualQuery("y.is_deleted", "N");
        query.getStringEqualQuery("z.is_deleted", "N");
        
        if(!"".equals(lovPmNoListDTO.getEquipId())||!"".equals(lovPmNoListDTO.getEquipDesc())){
        	query.append("AND x.pm_id IN (SELECT pm_id FROM TAPMEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("equip_id", "description||item_no", 
                    lovPmNoListDTO.getEquipId(), lovPmNoListDTO.getEquipDesc());
            query.append("									))					");
        }
//        query.append("ORDER by x.pm_id										                                ");
        query.getOrderByQuery("x.pm_id", lovPmNoListDTO.getOrderBy(), lovPmNoListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovPmNoListDTO.getIsLoadMaxCount(), lovPmNoListDTO.getFirstRow()));
    }
    public String findTotalCount(LovPmNoListDTO lovPmNoListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT																		");
        query.append("        count(*)																");
        query.append("FROM TAPMLST x LEFT OUTER JOIN TAPMEQUIP y 													");
		query.append("ON x.comp_no = y.comp_no                                     									");
		query.append(" AND x.pm_id = y.pm_id                      													");
		query.append("			INNER JOIN TAEQUIPMENT z															");
		query.append("          ON y.comp_no = z.comp_no															");
		query.append("           AND y.equip_id = z.equip_id														");
        query.append("WHERE   1=1																	");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.pm_type", conditionMap);
        query.getLikeQuery("x.pm_no", lovPmNoListDTO.getPmNo());
        query.getLikeQuery("x.description", lovPmNoListDTO.getPmDesc());
        query.getDeptLevelQuery("x.dept_id", lovPmNoListDTO.getDeptId(), lovPmNoListDTO.getDeptDesc(), loginUser.getCompNo());
       
        // 삭제여부
        query.getStringEqualQuery("x.is_deleted", "N");
        query.getStringEqualQuery("y.is_deleted", "N");
        query.getStringEqualQuery("z.is_deleted", "N");

        if(!"".equals(lovPmNoListDTO.getEquipId())||!"".equals(lovPmNoListDTO.getEquipDesc())){
        	query.append("AND x.pm_id IN (SELECT pm_id FROM TAPMEQUIP 								");
        	query.append("				WHERE equip_id IN (SELECT equip_id							");
        	query.append("									FROM TAEQUIPMENT						");
        	query.append("									WHERE 1=1								");
        	query.getCodeLikeQuery("equip_id", "description||item_no", 
                    lovPmNoListDTO.getEquipId(), lovPmNoListDTO.getEquipDesc());
            query.append("									))										");
        }
//        query.append("ORDER by x.pm_id																");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}