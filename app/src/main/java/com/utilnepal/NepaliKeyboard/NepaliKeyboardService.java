package com.utilnepal.NepaliKeyboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import com.utilnepal.R;

public class NepaliKeyboardService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    //initializing Keyboard
    private KeyboardView kv;
    private Keyboard keyboard;
    private EditorInfo sEditorInfo;

    //forCapsLock
    private  boolean isCaps = false;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard_preview,null);
        keyboard = new Keyboard(this,R.xml.keyboard_nepali);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case Keyboard.KEYCODE_DELETE:
                InputConnection ic = getCurrentInputConnection();
                ic.deleteSurroundingText(5, 0);
                return true;
            default:
                return false;
        }

    }


    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        sEditorInfo = info;
        super.onStartInputView(info, restarting);
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);
        switch (primaryCode)
        {
            case Keyboard.KEYCODE_DELETE:
                CharSequence selectedText = ic.getSelectedText(0);
                if (TextUtils.isEmpty(selectedText)) {
                    ic.deleteSurroundingText(1, 0);
                } else {
                    ic.commitText("", 1);
                }

                break;
            case Keyboard.KEYCODE_SHIFT:
                isCaps = !isCaps;
                keyboard.setShifted(isCaps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:

                switch (sEditorInfo.imeOptions & (EditorInfo.IME_MASK_ACTION|EditorInfo.IME_FLAG_NO_ENTER_ACTION)) {
                    case EditorInfo.IME_ACTION_GO:
                        ic.performEditorAction(EditorInfo.IME_ACTION_GO);
                        break;
                    case EditorInfo.IME_ACTION_NEXT:
                        ic.performEditorAction(EditorInfo.IME_ACTION_NEXT);
                        break;
                    case EditorInfo.IME_ACTION_SEARCH:
                        ic.performEditorAction(EditorInfo.IME_ACTION_SEARCH);
                        break;
                    case EditorInfo.IME_ACTION_SEND:
                        ic.performEditorAction(EditorInfo.IME_ACTION_SEND);
                        break;
                    default:
                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                        break;
                }

                break;
            case -999:
                keyboard = new Keyboard(this, R.xml.keyboard_nepali);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;

            case -1000:
                keyboard = new Keyboard(this, R.xml.esp_char_keyboard);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;
            case -1001:
                keyboard = new Keyboard(this, R.xml.more_esp_char_keyboard);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;
            case -1002:
                keyboard = new Keyboard(this,R.xml.main_keyboard_duplicate);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;

            case -2000:
                keyboard = new Keyboard(this,R.xml.nepali_symbol);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;
            case -3000:
                ic.commitText("क्ष",1);
                break;
            case -3001:
                ic.commitText("त्र",1);
                break;
            case -3002:
                ic.commitText("ज्ञ",1);
                break;
            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) && isCaps)
                    code = Character.toUpperCase(code);
                ic.commitText(String.valueOf(code),1);
        }

    }

    private void playClick(int primaryCode) {
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(primaryCode)
        {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }


}
