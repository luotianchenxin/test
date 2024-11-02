package videoTransmission

import android.content.res.Resources
import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.util.Log
import android.view.Surface
import com.example.rov.R
import java.io.File
import java.lang.IllegalStateException

class H264Player(surface: Surface){
    //创建一个解码器
    private val mediaCodec: MediaCodec = MediaCodec.createDecoderByType("video/avc")

    //设置视频大小
    private val mediaFormat = MediaFormat.createVideoFormat(
        MediaFormat.MIMETYPE_VIDEO_AVC,
        1280, 720
    )

    //创建视频数据接收类
    private var h264UDPClient: H264Client = H264Client()
    private val info = MediaCodec.BufferInfo()

    //设置解码参数
    init {
        mediaFormat.setInteger(MediaFormat.KEY_FRAME_RATE, 15)
        mediaFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT, 100)
        mediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, 30000000)
        mediaFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1)
        mediaCodec.configure(mediaFormat, surface, null, 0)
    }

    //将视频数据放入解码器
    private fun inputData(index: Int, frame_data:ByteArray, frame_data_size: Int) {
        mediaCodec.getInputBuffer(index)?.put(frame_data, 0, frame_data_size)
        mediaCodec.queueInputBuffer(index, 0, frame_data_size, 0, 0)
    }


    private fun cycleShow(){
        while (true) {
            //判断视频数据列表是否有数据
            val listSize = h264UDPClient.finallyList.size
            if (listSize > 0) {
                try{
                    val frameData = h264UDPClient.finallyList[0]
                    try {
                        val index = mediaCodec.dequeueInputBuffer(10000)
                        if (index >=0){
                            inputData(index, frameData, frameData.size)
                            if (listSize>1) {
                                h264UDPClient.deleteFirstFrame()
                            }
                            //mediaCodec.release()
                        }
                    }catch (e:IllegalStateException){
                        break
                    }
                }catch (e:NullPointerException){
                }
                        try{
                            //取出解码后的数据
                            val outIndex = mediaCodec.dequeueOutputBuffer(info, 0)
                            if (outIndex >= 0) {
                            mediaCodec.releaseOutputBuffer(outIndex, info.size != 0)}
                        }catch (e:IllegalStateException){
                            Log.d("debug","releaseOutputBuffer")
                        }



            }
    //        else{
//                val index = mediaCodec.dequeueInputBuffer(10000)
//                if (index >=0){
//                    inputData(index, backgroundImage, backgroundImage.size)
//                }
//                val outIndex = mediaCodec.dequeueOutputBuffer(info, 0)
//                if (outIndex >= 0) {
//                    mediaCodec.releaseOutputBuffer(outIndex, info.size != 0)
//                }
//            }
        }
    }

      fun startDoing() {
        Thread {
            h264UDPClient.startReceiveData()
            mediaCodec.start()
            cycleShow()
        }.start()
    }

    fun releaseMediaCodec(){
        mediaCodec.release()
        h264UDPClient.closeClient()
    }

}