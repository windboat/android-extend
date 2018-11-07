package com.guo.android_extend.image;

import android.graphics.Bitmap;

public class ImageConverter {
	private final String TAG = this.getClass().getSimpleName();

	public final static int CP_MJPEG 	= 0xA000;
	public final static int CP_PNG 		= 0xB000;
	public final static int CP_RGBA8888 = 1;
	public final static int CP_RGB565 	= 4;
	public final static int CP_RGBA4444 = 7;
	public final static int CP_PAF_NV21 = 0x802;
	public final static int CP_PAF_NV12 = 0x801;
	public final static int CP_PAF_YUYV = 0x501;
	public final static int CP_PAF_I420 = 0x601;
	public final static int CP_PAF_BGR24 = 0x201;


	private native int image_init(int width, int height, int format);
    private native int image_convert(Bitmap bitmap, byte[] data);
    private native int image_uninit();
	
	
	static {
		System.loadLibrary("image");
	}
	
	public ImageConverter() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param width convert image width
	 * @param height convert image height
	 * @param format  target to convert.
	 * @return success is true.
	 */
	public boolean initial(int width, int height, int format) {
        return -1 != image_init(width, height, format);
	}
	
	public boolean convert(Bitmap src, byte[] data) {
        return 0 == image_convert(src, data);
	}
	
	public void destroy() {
        image_uninit();
    }

	
	public static int calcImageSize(int w, int h, int format) {
		switch (format) {
		case CP_PAF_NV21 :
		case CP_PAF_NV12:
			return w * h * 3 / 2;
		case CP_PAF_YUYV:
		case CP_RGB565:
		case CP_RGBA4444:
			return w * h * 2;
		case CP_RGBA8888 :
		case CP_MJPEG:
			return w * h * 4;
		default :;
		}
		return 0;
	}
}
