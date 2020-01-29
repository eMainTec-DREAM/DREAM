package dream.asset.loc.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.loc.list.dao.MaEqLocListDAO;
import dream.asset.loc.list.dto.MaEqLocCommonDTO;

/**
 * 설비위치 - 목록 
 * @author  kim21017
 * @version $Id: MaEqLocListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqLocListDAOTarget"
 * @spring.txbn id="maEqLocListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqLocListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqLocListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqLocListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocCommonDTO
     * @return List
     */
    public List findEqLocList(MaEqLocCommonDTO maEqLocCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
//        query.append("SELECT * FROM (           ");
        query.append("SELECT b.*, LEVEL LVL, MIN(level) OVER() MINLVL FROM (    			");
        query.append("SELECT                                                                ");
        query.append("      DISTINCT(eqloc_id) 				AS ID,    						");
        query.append("        ''                            AS seqNo,                     	");
//        query.append("        'true' open,                                                     ");
        query.append("      '' 								AS isDelCheck,					");
        query.append("      x.eqloc_id 						AS eqLocId,                    	");
        query.append("      x.comp_no 						AS compNo,                    	");
        query.append("      x.eqloc_no    					AS eqLocNo,                    	");
        query.append("      x.description 					AS eqLocDesc,                  	");
        query.append("      SFACODE_TO_DESC(x.eqloc_lvl_type,'EQLOC_LVL_TYPE','SYS','','"+user.getLangId()+"')	AS levelType,	");
        query.append("      x.ord_no 						AS ordNo,                      	");
        query.append("      x.is_use 						AS isUse,                      	");
        query.append("      (SELECT description                                           	");
        query.append("       FROM TACTCTR                                               	");
        query.append("       WHERE ctctr_id = x.ctctr_id                                 	");
        query.append("         AND comp_no  = x.comp_no)    AS cpDesc,       				");
        query.append("      x.p_eqloc_id 					AS pEqlocId,                   	");
        query.append("      x.full_desc 					AS fullDesc                    	");
        query.append("FROM    TAEQLOC x                                                     ");
        query.append("WHERE 1=1                                                         	");
        query.append("START with  eqloc_id IN (SELECT a.eqloc_id FROM TAEQLOC a 			");
        query.append("                         WHERE  1 = 1                     			");
        query.append(this.getWhere(maEqLocCommonDTO,user));
        query.append("                         )                             			   	");
        query.append("CONNECT BY PRIOR p_eqloc_id = eqloc_id                    			");
        query.append("ORDER SIBLINGS BY  ord_no  											");
        query.append(" ) b                                                      			");
        query.append("WHERE 1 = 1                                               			");
        query.getAndQuery("eqLocId", maEqLocCommonDTO.getEqLocId());
        query.getLikeQuery("(SELECT full_desc FROM TAEQLOC WHERE comp_no=b.compNo AND eqloc_id=b.pEqlocId)", maEqLocCommonDTO.getFilterPEqLocDesc());
        if(!"".equals(maEqLocCommonDTO.getFilterPEqLocId()))
        {
            query.append(" START WITH pEqlocId = '"+maEqLocCommonDTO.getFilterPEqLocId()+"'");
        }
//        else if(!"".equals(maEqLocCommonDTO.getFilterPEqLocDesc()))
//        {
//            query.append(" START WITH pEqlocId = (SELECT a.eqloc_id FROM  TAEQLOC a WHERE a.eqloc_id = b.pEqlocId AND a.description like '%"+maEqLocCommonDTO.getFilterPEqLocDesc()+"%' )  ");
//        }
        else
            query.append("START WITH pEqlocId = 0");       

        query.append("CONNECT BY PRIOR eqLocId = pEqlocId                       			");
//        query.append(") c           ");
//        query.append("ORDER BY c.lvl, c.ordNo                                            ");
        query.append("ORDER BY TO_NUMBER(NVL(b.ordNo,'99999999'))  							");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaEqLocListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaEqLocCommonDTO maEqLocCommonDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        
        // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if ("".equals(maEqLocCommonDTO.getEqLocId()))
        {
            query.getAndQuery("x.eqloc_id", maEqLocCommonDTO.getEqLocId());
            
            query.getLikeQuery("x.eqloc_no", maEqLocCommonDTO.getFilterEqLocNo());
            query.getLikeQuery("x.description", maEqLocCommonDTO.getFilterEqLocDesc());
            
            query.getSysCdQuery("x.eqloc_lvl_type", maEqLocCommonDTO.getFilterLevelType(), maEqLocCommonDTO.getFilterLevelTypeDesc(),
                    "EQLOC_LVL_TYPE",maEqLocCommonDTO.getCompNo(),loginUser.getLangId());
        }
        
        
      //상위위치
//        query.getCodeLikeQuery("x.p_eqloc_id", "(SELECT a.full_desc FROM  TAEQLOC a WHERE a.eqloc_id = x.p_eqloc_id AND a.comp_no='"+maEqLocCommonDTO.getCompNo()+"')", 
//        		maEqLocCommonDTO.getFilterPEqLocId(), maEqLocCommonDTO.getFilterPEqLocDesc());
        
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqLocListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maEqLocCommonDTO
     * @return
     */
    public int deleteEqLoc(String id, MaEqLocCommonDTO maEqLocCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	String cdsysMId = id;
    	String compNo = user.getCompNo();
    	
    	query.append("DELETE FROM TAEQLOC				");
    	query.append("WHERE eqloc_id = '"+cdsysMId+"'	");
    	query.append("  AND comp_no	 = '"+compNo+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    public int resetFullDesc(MaEqLocCommonDTO maEqLocCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	String compNo = user.getCompNo();
    	
    	query.append("begin                                  ");
        query.append("SP_EQ_UPD_EQLOC_ALL('"+compNo+"');     ");
        query.append("end;                                   ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	return rtnValue;
    }
}