package dream.consult.comp.eqmgr.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.eqmgr.dao.MaEqMngListDAO;
import dream.consult.comp.eqmgr.dto.MaEqMngCommonDTO;

/**
 * 설비담당자변경 - 목록 dao
 * @author  jung7126
 * @version $Id: MaEqMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMngListDAOTarget"
 * @spring.txbn id="maEqMngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMngListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMngCommonDTO
     * @return List
     */
    public List findEqMngList(MaEqMngCommonDTO maEqMngCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maEqMngCommonDTO.getCompNo();
        
        query.append("SELECT															");
        query.append("		''											seqNo,			");
        query.append("		'' 											isDelCheck,		");
        query.append("		x.equip_id									equipId,		");
        query.append("		x.item_no									itemNo,			");
        query.append("		x.description								equipDesc,		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQLOC												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqloc_id = x.eqloc_id)				eqLocDesc,		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQCTG												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqctg_id = x.eqctg_id)				eqCtgDesc,		");
        query.append("		x.maker										maker,			");
        query.append("		x.model_no									modelNo,		");
        query.append("		SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')	plfTypeDesc,");
        query.append("		 x.is_law_eq								isLawEq,		");
        query.append("		(SELECT description											");
        query.append("		   FROM TADEPT												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND dept_id = x.dept_id)				deptDesc,		");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.main_mng_id)				mainMngName,	");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.sub_mng_id)				subMngName		");
        query.append("FROM   TAEQUIPMENT x												");
        query.append("WHERE  1=1														");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.append(this.getWhere(maEqMngCommonDTO, user));
        //query.append("ORDER by x.ord_no													");
        query.getOrderByQuery("x.ord_no", maEqMngCommonDTO.getOrderBy(), maEqMngCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMngCommonDTO.getIsLoadMaxCount(), maEqMngCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaEqMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMngCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaEqMngCommonDTO maEqMngCommonDTO,User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        String compNo = maEqMngCommonDTO.getCompNo();

        // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maEqMngCommonDTO.getEquipId()))
        {
            query.getAndQuery("x.equip_id", maEqMngCommonDTO.getEquipId());
            return query.toString();
        }
        // 설비명
        query.getLikeQuery("x.description", maEqMngCommonDTO.getFilterEquipDesc());
        // 설비번호
        query.getLikeQuery("x.item_no", maEqMngCommonDTO.getFilterItemNo());
        
        query.getLikeQuery("x.is_law_eq", maEqMngCommonDTO.getFilterIsLawEq());
        query.getLikeQuery("x.maker", maEqMngCommonDTO.getFilterMaker());
        query.getLikeQuery("x.model_no", maEqMngCommonDTO.getFilterModelNo());

        //위치
        //query.getEqLocLevelQuery("x.eqloc_id", maEqMngCommonDTO.getFilterEqLocId(), maEqMngCommonDTO.getFilterEqLocDesc(), compNo);
        query.getCodeLikeQuery("x.eqloc_id", "(SELECT full_desc FROM TAEQLOC WHERE comp_no = x.comp_no AND eqloc_id = x.eqloc_id)", maEqMngCommonDTO.getFilterEqLocId(), maEqMngCommonDTO.getFilterEqLocDesc());
        //관리부서
        //query.getDeptLevelQuery("x.dept_id", maEqMngCommonDTO.getFilterDeptId(), maEqMngCommonDTO.getFilterDeptDesc(), compNo);
        query.getCodeLikeQuery("x.dept_id", "(SELECT description FROM TADEPT WHERE comp_no = x.comp_no AND dept_id = x.dept_id)",  maEqMngCommonDTO.getFilterDeptId(), maEqMngCommonDTO.getFilterDeptDesc());

        //종류
        query.getEqCtgLevelQuery("x.eqctg_id", maEqMngCommonDTO.getFilterEqCtgId(), maEqMngCommonDTO.getFilterEqCtgDesc(), compNo);
        
        //내,외자 구분
        query.getSysCdQuery("x.plf_type", maEqMngCommonDTO.getFilterPlfTypeId(), maEqMngCommonDTO.getFilterPlfTypeDesc(), "PLF_TYPE", compNo,loginUser.getLangId());
        //관리자(정)
        query.getCodeLikeQuery("x.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.main_mng_id AND a.comp_no='"+compNo+"')", 
        		maEqMngCommonDTO.getFilterMainMngId(), maEqMngCommonDTO.getFilterMainMngName());
        //관리자(부)
        query.getCodeLikeQuery("x.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.sub_mng_id AND a.comp_no='"+compNo+"')", 
        		maEqMngCommonDTO.getFilterSubMngId(), maEqMngCommonDTO.getFilterSubMngName());
        return query.toString();
    }

	@Override
	public String findTotalCount(MaEqMngCommonDTO maEqMngCommonDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM   TAEQUIPMENT x			");
        query.append("WHERE  1=1					");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.append(this.getWhere(maEqMngCommonDTO, user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}

}