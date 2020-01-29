package dream.asset.loc.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class MaEqLocListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqLocListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  				");
        query.append("SELECT a.*, MIN(a.lvl) OVER() AS MINLVL FROM (        							");
        query.append("SELECT                                                                			");
        query.append("        x.eqloc_id                                          		AS ID,			");
        query.append("        ROW_NUMBER() OVER (ORDER BY ISNULL(x.ord_no, '99999999')) AS SEQNO,		");
//        query.append("        'true' open,                                                     ");
        query.append("        ''                    									AS ISDELCHECK,	");
        query.append("        x.eqloc_id                    							AS EQLOCID,     ");
        query.append("        x.eqloc_no                       							AS EQLOCNO,     ");
        query.append("        x.description                    							AS EQLOCDESC,   ");
        query.append("        dbo.SFACODE_TO_DESC(x.eqloc_lvl_type,'EQLOC_LVL_TYPE','SYS','','"+user.getLangId()+"')	AS LEVELTYPE,       ");
        query.append("        x.ord_no                    								AS ORDNO,   	");
        query.append("        x.is_use                    								AS ISUSE,       ");
        query.append("        (SELECT description                                           			");
        query.append("           FROM TACTCTR                                               			");
        query.append("          WHERE ctctr_id = x.ctctr_id                                 			");
        query.append("            AND comp_no  = x.comp_no)                				AS CPDESC,      ");
        query.append("      x.p_eqloc_id                    							AS PEQLOCID,    ");
        query.append("      x.full_desc                    								AS FULLDESC,    ");
        query.append("    --  MIN(y.lvl) OVER() 										AS MINLVL,      ");
        query.append("      y.lvl   													AS LVL          ");
        query.append("FROM    TAEQLOC x                                                     			");
        query.append("	  	,(SELECT * FROM dbo.SFAEQLOC_ALL('"+ maEqLocCommonDTO.getCompNo()+"','0')) y	");
        query.append("WHERE 1=1																			");
        query.append("AND 	x.EQLOC_ID = y.EQLOC_ID														");
        query.append(this.getWhere(maEqLocCommonDTO,user));
        query.append(" ) a        																		");
        query.append(" ORDER BY a.lvl asc, ISNULL(a.ORDNO, '99999999')        							");
        
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        
        // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maEqLocCommonDTO.getEqLocId()))
        {
            query.getAndQuery("x.eqloc_id", maEqLocCommonDTO.getEqLocId());
            return query.toString();
        }
        
        query.getLikeQuery("x.eqloc_no", maEqLocCommonDTO.getFilterEqLocNo());
        query.getLikeQuery("x.description", maEqLocCommonDTO.getFilterEqLocDesc());
      //상위위치
        query.getCodeLikeQuery("x.p_eqloc_id", "(SELECT a.full_desc FROM  TAEQLOC a WHERE a.eqloc_id = x.p_eqloc_id AND a.comp_no='"+maEqLocCommonDTO.getCompNo()+"')", 
        		maEqLocCommonDTO.getFilterPEqLocId(), maEqLocCommonDTO.getFilterPEqLocDesc());
        query.getSysCdQuery("x.eqloc_lvl_type", maEqLocCommonDTO.getFilterLevelType(), maEqLocCommonDTO.getFilterLevelTypeDesc(),
        					"EQLOC_LVL_TYPE",maEqLocCommonDTO.getCompNo(),loginUser.getLangId());
        
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String cdsysMId = id;
    	String compNo = user.getCompNo();
    	
    	query.append("DELETE FROM TAEQLOC				");
    	query.append("WHERE eqloc_id = '"+cdsysMId+"'	");
    	query.append("  AND comp_no	 = '"+compNo+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    public int resetFullDesc(MaEqLocCommonDTO maEqLocCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	String compNo = user.getCompNo();
    	
        query.append("EXEC dbo.SP_EQ_UPD_EQLOC_ALL '"+compNo+"'     ");
    	
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	return rtnValue;
    }
}