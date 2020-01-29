package dream.doc.img.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.doc.img.dao.MaDocImgListDAO;
import dream.doc.img.dto.MaDocImgCommonDTO;

/**
 * 첨부파일 - 목록 dao
 * @author  jung7126
 * @version $Id: MaDocImgListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maDocImgListDAOTarget"
 * @spring.txbn id="maDocImgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaDocImgListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaDocImgListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaDocImgListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocImgCommonDTO
     * @return List
     */
    public List findList(MaDocImgCommonDTO maDocImgCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maDocImgCommonDTO.getCompNo();
        maDocImgCommonDTO.setUserLang(user.getLangId());

        query.append("SELECT                                                                                    ");
        query.append("       '' as seqNo,                                                                       ");
        query.append("       '' isDelCheck,                                                                     ");
        query.append("       x.object_id imageNo,                                                               ");
        query.append("       x.description,                                                                     ");
        query.append("       SFACODE_TO_DESC(x.object_type , 'OBJECT_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') objectType,        ");
        query.append("       SFAIDTODESC(x.dept_id , '', 'DEPT', '"+compNo+"') deptId,                          ");
        query.append("       SFAIDTODESC(x.reg_id , '', 'EMP', '"+compNo+"') regId,                            ");
        query.append("       TO_CHAR(TO_DATE (x.reg_date,'yyyy-mm-dd'),'yyyy-mm-dd') regDate,                   ");
        query.append("       x.image_id imageId                                                                 ");
        query.append("FROM   TAIMAGE x                                                                          ");
        query.append("WHERE  1  = 1                                                                             ");
        query.append(this.getWhere(maDocImgCommonDTO));
//        query.append("ORDER BY x.image_id DESC  ");
        query.getOrderByQuery("x.image_id", maDocImgCommonDTO.getOrderBy(), maDocImgCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maDocImgCommonDTO.getIsLoadMaxCount(), maDocImgCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  jung7126
     * @version $Id: MaDocImgListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocImgCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaDocImgCommonDTO maDocImgCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("x.comp_no",maDocImgCommonDTO.getCompNo());
        if (!"".equals(maDocImgCommonDTO.getImageId()))
        {
            query.getAndQuery("x.image_id", maDocImgCommonDTO.getImageId());
            return query.toString();
        }
        query.getLikeQuery("x.description", maDocImgCommonDTO.getDescription());
        query.getDeptLevelQuery("x.dept_id", maDocImgCommonDTO.getDeptId(), maDocImgCommonDTO.getDeptDesc(), maDocImgCommonDTO.getCompNo());
        query.getCodeLikeQuery("x.reg_id", "SFAIDTODESC(x.reg_id , '', 'EMP', '"+maDocImgCommonDTO.getCompNo()+"')", 
                                maDocImgCommonDTO.getRegId(), maDocImgCommonDTO.getRegDesc());
        query.getAndQuery("x.object_id", maDocImgCommonDTO.getObjectId());
        query.getAndQuery("x.object_type", maDocImgCommonDTO.getObjectType());
        query.getAndQuery("x.sub_img_type", maDocImgCommonDTO.getSubImgType());
        query.getCodeLikeQuery("x.object_type", 
                               "SFACODE_TO_DESC(x.object_type , 'OBJECT_TYPE', 'SYS', '"+maDocImgCommonDTO.getCompNo()+"','"+maDocImgCommonDTO.getUserLang()+"')", 
                               maDocImgCommonDTO.getObjectTypeCode(), maDocImgCommonDTO.getObjectTypeDesc());
        
        return query.toString();
    }
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaDocImgListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteDoc(String id)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAIMAGE    				");
    	query.append("WHERE image_id = '"+id+"'		        ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }

    public int deleteDocData(String id)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE FROM TAIMGDATA                 ");
        query.append("WHERE  image_id = '"+id+"'            ");
        
        return this.getJdbcTemplate().update(query.toString());
    }

	@Override
	public String findTotalCount(MaDocImgCommonDTO maDocImgCommonDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("      count(1)            ");
        query.append("FROM   TAIMAGE x                                                                          ");
        query.append("WHERE  1  = 1                                                                             ");
        query.append(this.getWhere(maDocImgCommonDTO));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
}