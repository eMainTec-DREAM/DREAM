package dream.main.board.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 게시판 상세 DTO
 * @author  pochul2423
 * @version $Id: BoardPopupDetailDTO.java,v 1.3 2014/01/20 00:36:08 pochul2423 Exp $
 * @since   1.0
 *
 */
public class BoardPopupDetailDTO extends BaseDTO
{
    /** 게시번호 */
    private String boardNo   = "";
    /** 제목 */
    private String description   = "";
    /** 작성자 */
    private String enterBy   = "";
    
    private String enterByName  = "";
    /** 작성일 */
    private String enterDate   = "";
    /** 내용 */
    private String contents     = "";
    /** 게시번호 View */
    private String bdViewNo   = "";
    /** 삭제가능여부 */
    private String deletable   = "";
    
    /** 상위 게시번호 */
    private String parentNo   = "";
    
    public String getParentNo()
    {
        return parentNo;
    }
    public void setParentNo(String parentNo)
    {
        this.parentNo = parentNo;
    }
    public String getDeletable()
    {
        return deletable;
    }
    public void setDeletable(String deletable)
    {
        this.deletable = deletable;
    }
    public String getBdViewNo()
    {
        return bdViewNo;
    }
    public void setBdViewNo(String bdViewNo)
    {
        this.bdViewNo = bdViewNo;
    }
    public String getEnterByName()
    {
        return enterByName;
    }
    public void setEnterByName(String enterByName)
    {
        this.enterByName = enterByName;
    }
    
    public String getBoardNo()
    {
        return boardNo;
    }
    public void setBoardNo(String boardNo)
    {
        this.boardNo = boardNo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getEnterBy()
    {
        return enterBy;
    }
    public void setEnterBy(String enterBy)
    {
        this.enterBy = enterBy;
    }
    public String getEnterDate()
    {
        return enterDate;
    }
    public void setEnterDate(String enterDate)
    {
        this.enterDate = CommonUtil.convertDate(enterDate);
    }
    public String getContents()
    {
        return contents;
    }
    public void setContents(String contents)
    {
        this.contents = contents;
    } 
}