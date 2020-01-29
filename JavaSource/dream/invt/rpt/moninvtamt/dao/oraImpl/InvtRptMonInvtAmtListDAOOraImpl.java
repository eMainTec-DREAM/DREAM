package dream.invt.rpt.moninvtamt.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.invt.rpt.moninvtamt.dao.InvtRptMonInvtAmtListDAO;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtCommonDTO;

/**
 * InvtRptMonInvtAmt Page - List DAO implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="invtRptMonInvtAmtListDAOTarget"
 * @spring.txbn id="invtRptMonInvtAmtListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtRptMonInvtAmtListDAOOraImpl  extends BaseJdbcDaoSupportOra implements InvtRptMonInvtAmtListDAO
{

    /**
     * grid find
     * @author  cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptMonInvtAmtCommonDTO
     * @return List
     */
    public List findList(InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        
        query.append("SELECT        ");
        query.append("              ''                                                                          seqNo               ");
        query.append("             ,''                                                                         isDelCheck       ");
        query.append("            ,plant    ");
        query.append("            ,team ");
        query.append("            ,part ");
        query.append("            , separation  ");
        query.append("            , CATEGORY    ");
        query.append("            ,isPlan   ");
        query.append("            ,PROMPT   ");
        query.append("            ,invtlist_id invtlistId   ");
        query.append("            ,SUM(A.month1) month1                                                                     ");
        query.append("            ,SUM(A.month2) month2                                                                     ");
        query.append("            ,SUM(A.month3) month3                                                                     ");
        query.append("            ,SUM(A.month4) month4                                                                     ");
        query.append("            ,SUM(A.month5) month5                                                                     ");
        query.append("            ,SUM(A.month6) month6                                                                     ");
        query.append("            ,SUM(A.month7) month7                                                                     ");
        query.append("            ,SUM(A.month8) month8                                                                     ");
        query.append("            ,SUM(A.month9) month9                                                                     ");
        query.append("            ,SUM(A.month10) month10                                                                   ");
        query.append("            ,SUM(A.month11) month11                                                                   ");
        query.append("            ,SUM(A.month12) month12                                                                   ");
        query.append("            ,SUM(A.total)       total             ");
        query.append("FROM (    ");
        query.append("SELECT    ");
        query.append("            (SELECT description   ");
        query.append("             FROM TADEPT b    ");
        query.append("             WHERE b.dept_categ = 'L2'    ");
        query.append("             START WITH b.dept_id = z.usage_dept  ");
        query.append("             CONNECT BY PRIOR p_dept_id = dept_id ");
        query.append("            ) team    ");
        query.append("            ,(SELECT description  ");
        query.append("             FROM TADEPT b    ");
        query.append("             WHERE b.dept_categ = 'L3'    ");
        query.append("             START WITH b.dept_id = z.usage_dept  ");
        query.append("             CONNECT BY PRIOR p_dept_id = dept_id ");
        query.append("            ) part    ");
        query.append("            ,SFACODE_TO_DESC(z.invt_categ, 'INVT_CATEG', 'SYS', z.comp_no, 'ko') separation       ");
        query.append("            ,SFACODE_TO_DESC(z.invt_type, 'INVT_TYPE', 'SYS', z.comp_no, 'ko')     CATEGORY           ");
        query.append("            ,SFACODE_TO_DESC(z.invt_kind, 'INVT_KIND', 'SYS', z.comp_no, 'ko')     isPlan             ");
        query.append("            ,y.description                                                         PROMPT             ");
        query.append("            ,CASE WHEN SUBSTR(x.rec_date,5,2)='01'                                                            ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month1                                                         ");
        query.append("            , CASE WHEN SUBSTR(x.rec_date,5,2)='02'                                                   ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month2                                                         ");
        query.append("            , CASE WHEN SUBSTR(x.rec_date,5,2)='03'                                                   ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month3                                                         ");
        query.append("            , CASE WHEN SUBSTR(x.rec_date,5,2)='04'                                                   ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month4                                                         ");
        query.append("            , CASE WHEN SUBSTR(x.rec_date,5,2)='05'                                                   ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month5                                                         ");
        query.append("            , CASE WHEN SUBSTR(x.rec_date,5,2)='06'                                                   ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month6                                                         ");
        query.append("            , CASE WHEN SUBSTR(x.rec_date,5,2)='07'                                                   ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month7                                                         ");
        query.append("            , CASE WHEN SUBSTR(x.rec_date,5,2)='08'                                                   ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month8                                                         ");
        query.append("            , CASE WHEN SUBSTR(x.rec_date,5,2)='09'                                                   ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month9                                                         ");
        query.append("            , CASE WHEN SUBSTR(x.rec_date,5,2)='10'                                                   ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month10                                                        ");
        query.append("            , CASE WHEN SUBSTR(x.rec_date,5,2)='11'                                                   ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month11                                                        ");
        query.append("            , CASE WHEN SUBSTR(x.rec_date,5,2)='12'                                                   ");
        query.append("                                THEN x.tot_price                                                      ");
        query.append("                                ELSE 0                                                                ");
        query.append("                                END    month12                                                        ");
        query.append("             , x.tot_price      total         ");
        query.append("             ,z.invtlist_id       ");
        query.append("             ,c.description plant                                 ");
        query.append("FROM TAINVTLIST z LEFT OUTER JOIN TAPTPRRECLIST x ON z.invtlist_id = x.invtlist_id AND x.prreclist_status = 'C'");
        query.append(this.getYearWhere(invtRptMonInvtAmtCommonDTO, user));
        query.append("    LEFT OUTER JOIN TXDWPTCONTITEM y ON z.invtlist_id = y.invtlist_id ");
        query.append("    LEFT OUTER JOIN TAPLANT c ON z.plant = c.plant    ");
        query.append("    WHERE 1 = 1    ");
        query.append(this.getWhere(invtRptMonInvtAmtCommonDTO, user));
        query.append(") A   ");
        query.append("GROUP BY plant, team, part , separation  , CATEGORY ,isPlan   ,PROMPT ,invtlist_id    ");
        query.getOrderByQuery("invtlist_id desc", invtRptMonInvtAmtCommonDTO.getOrderBy(), invtRptMonInvtAmtCommonDTO.getDirection());
        
//        query.append("ORDER BY invtlist_id DESC ");

        
//        query.append("SELECT          																					");
//        query.append("      ''                                  										seqNo           ");
//        query.append("      ,''                                 										isDelCheck      ");
//        query.append("      , (SELECT b.description        																");
//        query.append("            FROM tadept b       																    ");
//        query.append("             WHERE b.comp_no=z.comp_no AND b.dept_id=(SELECT b.p_dept_id        					");
//        query.append("             FROM tadept b       																	");
//        query.append("             WHERE b.comp_no=z.comp_no AND b.dept_id=z.usage_dept)) 				team       		");
//        query.append("      , (SELECT b.dept_id        																	");
//        query.append("            FROM tadept b       																    ");
//        query.append("             WHERE b.comp_no=z.comp_no AND b.dept_id=(SELECT b.p_dept_id        					");
//        query.append("             FROM tadept b       																	");
//        query.append("             WHERE b.comp_no=z.comp_no AND b.dept_id=z.usage_dept)) 				deptId     		");
//        query.append("		,aa.INVTLIST_ID  															invtListId      ");
//        query.append("            ,(SELECT b.description        														");
//        query.append("            FROM tadept b        																	");
//        query.append("            WHERE b.comp_no=z.comp_no AND b.dept_id=z.usage_dept) 				part        	");
//        query.append("            ,SFACODE_TO_DESC(z.invt_categ, 'INVT_CATEG', 'SYS', aa.comp_no, '"+user.getLangId()+"') separation      ");
//        query.append("            ,SFACODE_TO_DESC(z.invt_type, 'INVT_TYPE', 'SYS', aa.comp_no, '"+user.getLangId()+"') 	category        ");
//        query.append("            ,SFACODE_TO_DESC(z.invt_kind, 'INVT_KIND', 'SYS', aa.comp_no, '"+user.getLangId()+"') 	isPlan        	");
//        query.append("            ,y.description	 													prompt        	");
//        query.append("            ,aa.month1        																	");
//        query.append("            ,aa.month2        																	");
//        query.append("            ,aa.month3        																	");
//        query.append("            ,aa.month4        																	");
//        query.append("            ,aa.month5        																	");
//        query.append("            ,aa.month6        																	");
//        query.append("            ,aa.month7        																	");
//        query.append("            ,aa.month8        																	");
//        query.append("            ,aa.month9        																	");
//        query.append("            ,aa.month10        																	");
//        query.append("            ,aa.month11        																	");
//        query.append("            ,aa.month12        																	");
//        query.append("            ,aa.total        																		");
//        query.append("FROM  (SELECT A.INVTLIST_ID        																");
//        query.append("        ,comp_no        																			");
//        query.append("            ,sum(a.month1) month1        															");
//        query.append("            ,sum(a.month2) month2        															");
//        query.append("            ,sum(a.month3) month3        															");
//        query.append("            ,sum(a.month4) month4        															");
//        query.append("            ,sum(a.month5) month5        															");
//        query.append("            ,sum(a.month6) month6        															");
//        query.append("            ,sum(a.month7) month7        															");
//        query.append("            ,sum(a.month8) month8        															");
//        query.append("            ,sum(a.month9) month9        															");
//        query.append("            ,sum(a.month10) month10        														");
//        query.append("            ,sum(a.month11) month11        														");
//        query.append("            ,sum(a.month12) month12        														");
//        query.append("            ,sum(a.total)	   total	        													");
//        query.append("FROM (SELECT X.PRRECLIST_ID        																");
//        query.append("            ,X.INVTLIST_ID        																");
//        query.append("            ,x.comp_no        																	");
//        query.append("    ,CASE WHEN substr(x.rec_date,5,2)='01'        												");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month1        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='02'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month2        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='03'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month3        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='04'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month4        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='05'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month5        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='06'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month6        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='07'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month7        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='08'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month8        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='09'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month9        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='10'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month10        											");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='11'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month11        											");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='12'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month12        											");
//        query.append("             , x.tot_price      total	        													");
//        query.append("FROM taptprreclist x        																		");
//        query.append("WHERE prreclist_status='C'        																");
//        query.append(this.getYearWhere(invtRptMonInvtAmtCommonDTO, user));
//        query.append(")   a         																					");
//        query.append("GROUP BY a.INVTLIST_ID,a.comp_no        															");
//        query.append(") aa LEFT OUTER JOIN tainvtlist z        															");
//        query.append("          ON aa.comp_no=z.comp_no AND  aa.invtlist_id = z.invtlist_id        						");
//        query.append("           LEFT OUTER JOIN TAINVTITEMS y        													");
//        query.append("          ON aa.comp_no = y.comp_no AND aa.invtlist_id = y.invtlist_id        					");
//        query.append("WHERE 1=1 																						");
//        query.append(this.getWhere(invtRptMonInvtAmtCommonDTO, user));
//        query.getOrderByQuery("z.description", invtRptMonInvtAmtCommonDTO.getOrderBy(), invtRptMonInvtAmtCommonDTO.getDirection());
//        
        return getJdbcTemplate().queryForList(query.toString(invtRptMonInvtAmtCommonDTO.getIsLoadMaxCount(), invtRptMonInvtAmtCommonDTO.getFirstRow()));
    }

    
    private String getYearWhere(InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO, User user)
    {        
    	QueryBuffer query = new QueryBuffer();

    	query.getAndQuery("substr(x.rec_date,0,4)", invtRptMonInvtAmtCommonDTO.getFilterYyyy());
    	

    	return query.toString();
    }
    
    /**
     * Filter 조건
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     *   
     * @param invtRptMonInvtAmtCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();

        //공장
        query.getCodeLikeQuery("z.plant", "(SELECT description FROM  taplant b  WHERE b.comp_no = '"+user.getCompNo()+"' AND b.plant = z.plant )", 
                invtRptMonInvtAmtCommonDTO.getFilterPlantId(), invtRptMonInvtAmtCommonDTO.getFilterPlantDesc());
        
        //사용부서
//        query.getCodeLikeQuery("z.usage_dept", "(SELECT description FROM  tadept b  WHERE b.comp_no = '"+user.getCompNo()+"' AND b.dept_id = z.usage_dept )", 
//                invtRptMonInvtAmtCommonDTO.getFilterDeptId(), invtRptMonInvtAmtCommonDTO.getFilterDeptDesc());
        
        query.getDeptLevelQuery("z.usage_dept", invtRptMonInvtAmtCommonDTO.getFilterDeptId(), invtRptMonInvtAmtCommonDTO.getFilterDeptDesc(), user.getCompNo());
        return query.toString();
    }

    public String findTotalCount(
            InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();        
//        query.append("select count(1)      																				");
//        query.append("FROM  (SELECT A.INVTLIST_ID        																");
//        query.append("        ,comp_no        																			");
//        query.append("            ,sum(a.month1) month1        															");
//        query.append("            ,sum(a.month2) month2        															");
//        query.append("            ,sum(a.month3) month3        															");
//        query.append("            ,sum(a.month4) month4        															");
//        query.append("            ,sum(a.month5) month5        															");
//        query.append("            ,sum(a.month6) month6        															");
//        query.append("            ,sum(a.month7) month7        															");
//        query.append("            ,sum(a.month8) month8        															");
//        query.append("            ,sum(a.month9) month9        															");
//        query.append("            ,sum(a.month10) month10        														");
//        query.append("            ,sum(a.month11) month11        														");
//        query.append("            ,sum(a.month12) month12        														");
//        query.append("            ,sum(a.total)	   total	        													");
//        query.append("FROM (SELECT X.PRRECLIST_ID        																");
//        query.append("            ,X.INVTLIST_ID        																");
//        query.append("            ,x.comp_no        																	");
//        query.append("    ,CASE WHEN substr(x.rec_date,5,2)='01'        												");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month1        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='02'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month2        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='03'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month3        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='04'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month4        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='05'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month5        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='06'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month6        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='07'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month7        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='08'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month8        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='09'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month9        												");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='10'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month10        											");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='11'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month11        											");
//        query.append("            , CASE WHEN substr(x.rec_date,5,2)='12'        										");
//        query.append("                                THEN x.tot_price        											");
//        query.append("                                ELSE 0        													");
//        query.append("                                END    month12        											");
//        query.append("             , x.tot_price      total	        													");
//        query.append("FROM taptprreclist x        																		");
//        query.append("WHERE prreclist_status='C'        																");
//        query.append(")   a         																					");
//        query.append("GROUP BY a.INVTLIST_ID,a.comp_no        															");
//        query.append(") aa LEFT OUTER JOIN tainvtlist z        															");
//        query.append("          ON aa.comp_no=z.comp_no AND  aa.invtlist_id = z.invtlist_id        						");
//        query.append("           LEFT OUTER JOIN TAINVTITEMS y        													");
//        query.append("          ON aa.comp_no = y.comp_no AND aa.invtlist_id = y.invtlist_id        					");
//        query.append("WHERE 1=1 																						");
//        query.append(this.getWhere(invtRptMonInvtAmtCommonDTO, user));
//        
        query.append("SELECT COUNT(1)       ");
        query.append("FROM (    ");
        query.append("SELECT        ");
        query.append("             1               ");
        query.append("FROM (    ");
        query.append("SELECT    ");
        query.append("            (SELECT description   ");
        query.append("             FROM TADEPT b    ");
        query.append("             WHERE b.dept_categ = 'L2'    ");
        query.append("             START WITH b.dept_id = z.usage_dept  ");
        query.append("             CONNECT BY PRIOR p_dept_id = dept_id ");
        query.append("            ) team    ");
        query.append("            ,(SELECT description  ");
        query.append("             FROM TADEPT b    ");
        query.append("             WHERE b.dept_categ = 'L3'    ");
        query.append("             START WITH b.dept_id = z.usage_dept  ");
        query.append("             CONNECT BY PRIOR p_dept_id = dept_id ");
        query.append("            ) part    ");
        query.append("            ,SFACODE_TO_DESC(z.invt_categ, 'INVT_CATEG', 'SYS', z.comp_no, 'ko') separation       ");
        query.append("            ,SFACODE_TO_DESC(z.invt_type, 'INVT_TYPE', 'SYS', z.comp_no, 'ko')     CATEGORY           ");
        query.append("            ,SFACODE_TO_DESC(z.invt_kind, 'INVT_KIND', 'SYS', z.comp_no, 'ko')     isPlan             ");
        query.append("            ,y.description                                                         PROMPT             ");
        query.append("            ,z.plant                                                                      ");
        query.append("            ,z.invtlist_id                                                                      ");
        query.append("FROM TAINVTLIST z LEFT OUTER JOIN TAPTPRRECLIST x ON z.invtlist_id = x.invtlist_id AND x.prreclist_status = 'C'");
        query.append(this.getYearWhere(invtRptMonInvtAmtCommonDTO, user));
        query.append("    LEFT OUTER JOIN TXDWPTCONTITEM y ON z.invtlist_id = y.invtlist_id ");
        query.append("    LEFT OUTER JOIN TAPLANT c ON z.plant = c.plant    ");
        query.append("    WHERE 1 = 1    ");
        query.append(this.getWhere(invtRptMonInvtAmtCommonDTO, user));
        query.append(") A   ");
        query.append("GROUP BY plant, team, part , separation  , CATEGORY ,isPlan   ,PROMPT ,invtlist_id    ");
//        query.getOrderByQuery("invtlist_id desc", invtRptMonInvtAmtCommonDTO.getOrderBy(), invtRptMonInvtAmtCommonDTO.getDirection());
        query.append(")    ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
