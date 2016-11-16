package com.faceye.component.cms.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.faceye.component.cms.entity.PersonLabel;
import com.faceye.component.cms.service.PersonLabelService;
import com.faceye.component.cms.service.WeixinAppService;
import com.faceye.feature.util.RandUtil;

@Service
public class WeixinAppServiceImpl implements WeixinAppService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Value("#{property['wx.fonts.file.path']}")
	private String fontFilePath = "";
	//星座
	@Value("#{property['wx.qrcode.path']}")
	private String qrCodePath = "";
	
	//搞T
	@Value("#{property['wx.qt.qrcode.path']}")
	private String gtQrCodePath="";
	@Value("#{property['wx.xizuo.img.path']}")
	private String imgPath = "";

	private int nameSize = 75;
	// 是否加载字体库
	private Boolean isLoadFont = false;
	@Autowired
	private PersonLabelService personLabelService = null;

	@Override
	public void writePersonal2Stream(Map params, int width, int height, OutputStream outputStream) {
		this.loadFont(fontFilePath, 1f);
		String name = MapUtils.getString(params, "name");
		if (StringUtils.isEmpty(name)) {
			name = "无名氏";
		}
		String s = "我是Jason";
		// FontMetrica fm=null;
		File file = new File("/tmp/img-bg" + RandUtil.getRandInt(1, 1000) + ".jpg");
		// Font font = new Font("Serif", Font.BOLD, 40);
		Font font = new Font("黑体-繁 中等", Font.PLAIN, 100);
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = (Graphics2D) bi.getGraphics();
		gd.setBackground(Color.black);
		gd.clearRect(0, 0, width, height);
		FontRenderContext context = gd.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(s, context);
		// double x = (width - bounds.getWidth()) / 2;
		// // double y = (height - bounds.getHeight()) / 2;
		double top = 15;
		double ascent = -bounds.getY();
		double baseY = top + ascent;
		// g2.setFont(font);
		// g2.drawString(s, (int) x, (int) baseY);
		int centerY = (height - (int) baseY) / 2;
		centerY -= 100;
		int centerX = (int) (width - 30 - bounds.getWidth() - bounds.getX()) / 2;
		List<String> words = this.getLabels();
		if (CollectionUtils.isEmpty(words)) {
			words = this.randString();
		}
		int _x = 0;
		int _y = (int) baseY;
		boolean isShowUsername = false;
		// for (String text : words) {
		// int randSize = this.randSize();
		// this.setText(text, randSize, width, height, _y, gd, null);
		// if (_y < centerY && _y + 65 > centerY) {
		// _y += 100;
		// this.setText("我是邓小平", 85, width, height, _y, gd, Color.white);
		// _y += 100;
		// } else {
		// _y += randSize + 15;
		// }
		// }
		int y = 65;
		while (CollectionUtils.isNotEmpty(words)) {
			List<Map<String, String>> lineTexts = this.getLineTexts(words, gd, width);
			if (CollectionUtils.isNotEmpty(lineTexts)) {
				int maxSize = this.setTexts(lineTexts, width, gd, y);
				y += maxSize + 15;
			}
			if (y < centerY && y + 80 > centerY) {
				gd.setPaint(Color.white);
				y += 40;
				// this.setText("我是邓小平", 85, width, height, _y, gd, Color.white);
				this.setText("T叫" + name, nameSize, centerX, y, gd, null);
				y += 80;
			}

			for (int i = 0; i < lineTexts.size(); i++) {
				words.remove(0);
			}
		}
		gd.setPaint(Color.white);
		// gd.setFont(font);
		// this.setText("----------------------------------------------------------------------------------", 55, 0, 650, gd, null);
		gd.setPaint(Color.lightGray);
//		 this.setText(" <搞T让T爆表!", 30, 140, 550, gd,null);
		this.setText(" <搞T让T爆表!", 30, 310, 860, gd,null);
		try {
			ImageIO.write(bi, "jpg", file);
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
		BufferedImage image = mergeImage(file, new File(gtQrCodePath), width, height);
		if (image != null) {
			try {
				if (outputStream != null) {
					ImageIO.write(image, "jpg", outputStream);
					// ImageIO.write(ImageIO.read(file), "jpg", outputStream);
				}
			} catch (IOException e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
		}
		// mergeImage(file, new File("/work/29.pic.jpg"),width,height);
		// mergeImage(file, new File("/work/38.pic.jpg"), width, height);
	}

	private List<Map<String, String>> getLineTexts(List<String> words, Graphics2D gd, int width) {
		List<Map<String, String>> res = new ArrayList<Map<String, String>>(0);
		int usedWidth = 10;
		for (String text : words) {
			Map<String, String> map = new HashMap();
			Integer size = this.randSize();
			FontRenderContext context = gd.getFontRenderContext();
			Font font = new Font("黑体-繁 中等", Font.PLAIN, size);
			Rectangle2D bounds = font.getStringBounds(text, context);
			int textWidth = (int) bounds.getWidth() + (int) bounds.getX();
			usedWidth += textWidth + bounds.getX() + 50;
			if (usedWidth < width) {
				map.put("text", text);
				map.put("size", "" + size);
				map.put("width", "" + textWidth);
				res.add(map);
			} else {
				break;
			}
		}

		return res;
	}

	private int setTexts(List<Map<String, String>> lineTexts, int width, Graphics2D gd, int y) {
		int maxSize = 0;
		// int x = 10;
		int textWidth = 0;
		for (Map<String, String> map : lineTexts) {
			textWidth += MapUtils.getIntValue(map, "width");
			textWidth += 0;
		}
		int x = (width - 180 - textWidth) / 2;
		for (Map<String, String> map : lineTexts) {
			String text = MapUtils.getString(map, "text");
			Integer size = MapUtils.getInteger(map, "size");
			Color color = this.randColor();
			if (size > maxSize) {
				maxSize = size;
			}
			gd.setPaint(color);
			Font font = new Font("黑体-繁 中等", Font.PLAIN, size);
			gd.setFont(font);
			FontRenderContext context = gd.getFontRenderContext();
			Rectangle2D bounds = font.getStringBounds(text, context);
			this.setText(text, size, x, y, gd, null);
			x += bounds.getWidth() + bounds.getX();
			x += 50;
		}
		return maxSize;
	}

	private void setText(String text, int size, int x, int y, Graphics2D gd, Font font) {

		char[] chars = text.toCharArray();
		// gd.setPaint(color);
		// Font font = new Font("黑体-繁 中等", Font.PLAIN, size);
		if (font == null) {
			font = this.loadFont(this.fontFilePath, size);
		}
		gd.setFont(font);
		// gd.setFont(font);
		// FontRenderContext context = gd.getFontRenderContext();
		// Rectangle2D bounds = font.getStringBounds(text, context);
		// double top = 15;
		// double ascent = -bounds.getY();
		// double baseY = top + ascent;
		// int baseX = (int) ((width - bounds.getWidth()) / 2);
		// int centerX = (int) (width - bounds.getWidth() - bounds.getX()) / 2;
		// int x=0;
		for (int i = 0; i < chars.length; i++) {
			// logger.debug(">>FaceYe --> Char is:" + chars[i]);
			gd.drawString(String.valueOf(chars[i]), x, y);
			x += size;
		}
	}

	private int randSize() {
		return RandUtil.getRandInt(30, 50);
	}

	private Color randColor() {
		Color[] colors = { Color.lightGray, Color.DARK_GRAY, Color.green, Color.LIGHT_GRAY, Color.magenta, Color.yellow, Color.red, Color.PINK, Color.orange };
		int rand = RandUtil.getRandInt(0, colors.length);
		// logger.debug(">>Color index is:" + rand);
		return colors[rand];
	}

	private BufferedImage mergeImage(File file1, File file2, int width, int height) {
		BufferedImage img = null;
		BufferedImage image1 = null;
		String fileName = "img-end-" + RandUtil.getRandInt(1, 1000) + ".jpg";
		try {
			image1 = ImageIO.read(file1);
			BufferedImage image2 = ImageIO.read(file2);
			BufferedImage combined = new BufferedImage(image1.getWidth(), image1.getHeight(), BufferedImage.TYPE_INT_RGB);
			// paint both images, preserving the alpha channels
			// Graphics g = combined.getGraphics();
			Graphics2D g = (Graphics2D) combined.getGraphics();
			g.setBackground(Color.black);
			g.drawImage(image1, 0, 0, null);
			g.drawImage(image2, 5, height - image2.getHeight() - 5, null);
			// Save as new image
			ImageIO.write(combined, "JPG", new File("/tmp/", fileName));

		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
		try {
			img = ImageIO.read(new File("/tmp/" + fileName));
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
		return img;
	}

	private List<String> randString() {
		List<String> items = new ArrayList<String>(0);
		items.add("逗B");
		items.add("贼有财");
		items.add("卡哇伊");
		items.add("坤");
		items.add("走你");
		items.add("缺爱");
		items.add("女王");
		items.add("蛋疼!");
		items.add("手机控");
		items.add("朋友多");
		items.add("装B不讲理");
		items.add("关你屁事");
		items.add("老子有财");
		items.add("豪吞四海");
		items.add("B格有限");
		return items;
	}

	private Font loadFont(String filepath, float size) {
		// if (!this.isLoadFont) {
		try {
			File file = new File(filepath);
			FileInputStream aixing = new FileInputStream(file);
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
			Font dynamicFontPt = dynamicFont.deriveFont(size);
			aixing.close();
			return dynamicFontPt;
		} catch (Exception e)// 异常处理
		{
			logger.debug(">>FaceYe -->", e);
			return new java.awt.Font("微软雅黑", Font.PLAIN, 14);
		}
		// }else{
		// return null;
		// }
	}

	private Font font() {
		String root = System.getProperty("user.dir");// 项目根目录路径
		Font font = loadFont(root + "/data/PRISTINA.ttf", 18f);// 调用
		return font;
	}

	List<PersonLabel> personLabels = new ArrayList<PersonLabel>(0);

	private List<String> getLabels() {
		List<String> labels = new ArrayList<String>(0);
		try {
			if (CollectionUtils.isEmpty(personLabels) || personLabels.size() < 100) {
				Page<PersonLabel> page = this.personLabelService.getPage(null, 1, 1000);
				if (CollectionUtils.isNotEmpty(page.getContent())) {
					personLabels = page.getContent();
				}
			}
			if (CollectionUtils.isNotEmpty(personLabels)) {
				int start = RandUtil.getRandInt(0, personLabels.size() / 2);
				// int end = RandUtil.getRandInt(personLabels.size() / 2, personLabels.size());
				int size = 30;
				int end = start + size;

				if (end > personLabels.size()) {
					end = personLabels.size();
				}
				for (int i = start; i < end; i++) {
					labels.add(personLabels.get(i).getLabel());
				}
			}
			Collections.shuffle(labels);
		} catch (Exception e) {
			logger.error(">>FaceYe --> ", e);
		}
		return labels;
	}
	///////////////////////////////////// 星座///////////////////////////////////////////////
	// 3月21日~4月19日，白羊座
	// 4月20日~5月20日，金牛座
	// 5月21日~6月20日，双子座
	// 6月21日~7月21日，巨蟹座
	// 7月22日~8月22日，狮子座
	// 8月23日~9月22日，处女座
	// 9月23日~10月22日，天秤座
	// 10月23日~11月21日，天蝎座
	// 11月22日~12月21日，射手座
	// 12月22日~1月19日，摩羯座
	// 1月20日~2月18日，水瓶座
	// 2月19日~3月20日，双鱼座

	public void writePersonalXinzuoceshi2Stream(Map params, int width, int height, OutputStream outputStream) {
		String year = MapUtils.getString(params, "year");
		String month = MapUtils.getString(params, "month");
		String day = MapUtils.getString(params, "day");
		String name = MapUtils.getString(params, "name");
		if(StringUtils.isEmpty(name)){
			name="无名氏";
		}
		if (StringUtils.length(day) == 1) {
			day = "0" + day;
		}
		if (StringUtils.length(month) == 1) {
			month = "0" + month;
		}
		Integer m = Integer.parseInt(month);
		Integer d = Integer.parseInt(day);
		Map<String, String> xingzuo = this.getXingzuoByDate(month, day);
		File file = new File("/tmp/img-xingzuo-bg" + RandUtil.getRandInt(1, 1000) + ".jpg");
		// Font font = new Font("Serif", Font.BOLD, 40);
		Font font = new Font("Serif", Font.BOLD, 80);
		// BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// Graphics2D gd = (Graphics2D) bi.getGraphics();
		// gd.setBackground(Color.black);
		// gd.clearRect(0, 0, width, height);

		String showName = "我是" + name;
		BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		BufferedImage xingzuo1 = null;
		Graphics2D g = (Graphics2D) combined.getGraphics();
		g.clearRect(0, 0, width, height);
		g.setBackground(Color.black);
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(showName, context);
		g.drawImage(combined, 0, 0, null);
		String fileName = "img-xingzuo-" + RandUtil.getRandInt(1, 1000) + ".jpg";
		try {
			xingzuo1 = ImageIO.read(new File(imgPath + xingzuo.get("name") + "1.jpg"));
			g.drawImage(xingzuo1, 25, 0, null);
			int centerX = (int) (width - 30 - bounds.getWidth() - bounds.getX()) / 2;
			// height-
			this.setText(showName, 80, centerX+25, xingzuo1.getHeight() + 100, g, null);
			BufferedImage xingzuo2 = ImageIO.read(new File(imgPath + xingzuo.get("name") + "2.jpg"));
			g.drawImage(xingzuo2, 25, xingzuo1.getHeight() + 90, null);
			this.addQrCode(g, width, height);
			ImageIO.write(combined, "JPG", new File("/tmp/", fileName));
			if (outputStream != null) {
				ImageIO.write(ImageIO.read(new File("/tmp/", fileName)), "jpg", outputStream);
			}
		} catch (IOException e1) {
			logger.error(">>FaceYe Throws Exception:", e1);
		}
		// try {
		// ImageIO.write(combined, "jpg", new File("/tmp/"+fileName));
		// } catch (IOException e) {
		// logger.error(">>FaceYe Throws Exception:",e);
		// }
	}

	/**
	 * 添加二维码
	 * 
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年7月17日 上午10:47:18
	 */
	private void addQrCode(Graphics2D g, int width, int height) {
		BufferedImage qrCodeImg;
		try {
			qrCodeImg = ImageIO.read(new File(this.qrCodePath));
			g.drawImage(qrCodeImg, 5, height - qrCodeImg.getHeight() - 5, null);
			Font font = new Font("Serif", Font.BOLD, 25);
			this.setText("长安识别,生成个性签名", 25, 155, height - qrCodeImg.getHeight() + 65, g, null);
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}

	}

	/**
	 * 根据日期取得星座
	 * 
	 * @param date
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年7月17日 上午8:05:30
	 */
	private Map<String, String> getXingzuoByDate(String month, String day) {

		// month->mm
		// day:dd
		List<Map<String, String>> xingzuos = this.buildXingzuo();
		String date = month + day;
		int date2Int = Integer.parseInt(date);
		Map<String, String> entity = null;
		for (Map<String, String> map : xingzuos) {
			String start = MapUtils.getString(map, "start");
			String end = MapUtils.getString(map, "end");
			int start2Int = Integer.parseInt(start);
			int end2Int = Integer.parseInt(end);
			if (date2Int <= 119 || date2Int >= 1122) {
				entity = xingzuos.get(11);
				break;
			} else {
				if (date2Int >= start2Int && date2Int <= end2Int) {
					entity = map;
					break;
				}
			}
		}
		return entity;
	}

	private List<Map<String, String>> buildXingzuo() {
		List<Map<String, String>> res = new ArrayList<Map<String, String>>(0);
		Map<String, String> map0 = new HashMap<String, String>();
		map0.put("start", "120");
		map0.put("end", "218");
		map0.put("src", "src");
		map0.put("name", "水瓶");
		res.add(map0);

		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("start", "219");
		map1.put("end", "320");
		map1.put("src", "src");
		map1.put("name", "双鱼");
		res.add(map1);

		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("start", "321");
		map2.put("end", "419");
		map2.put("src", "src");
		map2.put("name", "白羊");
		res.add(map2);

		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("start", "420");
		map3.put("end", "520");
		map3.put("src", "src");
		map3.put("name", "金牛");
		res.add(map3);

		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("start", "521");
		map4.put("end", "620");
		map4.put("src", "src");
		map4.put("name", "双子");
		res.add(map4);

		Map<String, String> map5 = new HashMap<String, String>();
		map5.put("start", "621");
		map5.put("end", "721");
		map5.put("src", "src");
		map5.put("name", "巨蟹");
		res.add(map5);

		Map<String, String> map6 = new HashMap<String, String>();
		map6.put("start", "722");
		map6.put("end", "822");
		map6.put("src", "src");
		map6.put("name", "狮子");
		res.add(map6);
		Map<String, String> map7 = new HashMap<String, String>();
		map7.put("start", "823");
		map7.put("end", "922");
		map7.put("src", "src");
		map7.put("name", "处女座");
		res.add(map7);
		Map<String, String> map8 = new HashMap<String, String>();
		map8.put("start", "923");
		map8.put("end", "1022");
		map8.put("src", "src");
		map8.put("name", "天秤");
		res.add(map8);
		Map<String, String> map9 = new HashMap<String, String>();
		map9.put("start", "1023");
		map9.put("end", "1121");
		map9.put("src", "src");
		map9.put("name", "天蝎");
		res.add(map9);
		Map<String, String> map10 = new HashMap<String, String>();
		map10.put("start", "1122");
		map10.put("end", "1221");
		map10.put("src", "src");
		map10.put("name", "射手");
		res.add(map10);
		// 较特殊
		Map<String, String> map11 = new HashMap<String, String>();
		map11.put("start", "1123");
		map11.put("end", "119");
		map11.put("src", "src");
		map11.put("name", "摩羯");
		res.add(map11);
		return res;
	}
}
