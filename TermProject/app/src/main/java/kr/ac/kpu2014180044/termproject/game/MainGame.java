package kr.ac.kpu2014180044.termproject.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.kpu2014180044.termproject.R;
import kr.ac.kpu2014180044.termproject.framework.GameObject;
import kr.ac.kpu2014180044.termproject.ui.GameView;
import kr.ac.kpu2014180044.termproject.utils.CollisionHelper;

public class MainGame {
    public static int DIR_LEFT = -1;
    public static int DIR_RIGHT = 1;
    private static final String TAG = MainGame.class.getSimpleName();
    // singleton
    private static MainGame instance;
    public Score score;

    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }
    public float frameTime;
    private boolean initialized;

    public static Player player;
    public static Player getPlayer() {
        return player;
    }
    public static ArrayList<ArrayList<GameObject>> layers;
    public static ArrayList<ArrayList<GameObject>> getLayers() { return layers; }
//    private static HashMap<Class, ArrayList<GameObject>> recycleBin = new HashMap<>();

//    public void recycle(GameObject object) {
//        Class clazz = object.getClass();
//        ArrayList<GameObject> array = recycleBin.get(clazz);
//        if (array == null) {
//            array = new ArrayList<>();
//            recycleBin.put(clazz, array);
//        }
//        array.add(object);
//    }
//    public GameObject get(Class clazz) {
//        ArrayList<GameObject> array = recycleBin.get(clazz);
//        if (array == null || array.isEmpty()) return null;
//        return array.remove(0);
//    }

    public enum Layer {
        bg1, bg2, enemy, brick, player, ui, controller, ENEMY_COUNT
    }
    public boolean initResources() {
        if (initialized) {
            return false;
        }
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.ENEMY_COUNT.ordinal());

        player = new Player(575, 1700, DIR_LEFT);
//        //layers.get(Layer.player.ordinal()).add(player);
        add(Layer.player, player);
//        add(Layer.controller, new EnemyGenerator());

        int margin = (int) (20 * GameView.MULTIPLIER);
        score = new Score(w - margin, margin);
        score.setScore(1);
        add(Layer.ui, score);

        VerticalScrollBackground bg = new VerticalScrollBackground(R.mipmap.bg_city, 10);
        add(Layer.bg1, bg);
        VerticalScrollBackground clouds = new VerticalScrollBackground(R.mipmap.clouds, 20);
        add(Layer.bg2, clouds);

        // Brick
        float startX = 550.0f;
        float startY = 1800.0f;
        float offsetX = 150.0f;
        float offsetY = 75.0f;

        // 1
        for (int i = 0; i < 3; ++i) {
            Brick brick;
            if ( i == 3 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);
            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }
        // 2
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 4; ++i) {
            Brick brick;
            if ( i == 4 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);
            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }
        // 3
        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 4; ++i) {
            Brick brick;
            if ( i == 4 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }
        // 4
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }
        // 5
        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }
        // 6
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 4; ++i) {
            Brick brick;
            if ( i == 4 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        // 7
        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }

        // 8
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        // 9
        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 3; ++i) {
            Brick brick;
            if ( i == 3 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }

        // 10
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        // 11
        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 3; ++i) {
            Brick brick;
            if ( i == 3 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }

        // 12
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 3; ++i) {
            Brick brick;
            if ( i == 3 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 3; ++i) {
            Brick brick;
            if ( i == 3 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }


        // 13
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 3; ++i) {
            Brick brick;
            if ( i == 3 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }

        // 14
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 3; ++i) {
            Brick brick;
            if ( i == 3 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 3; ++i) {
            Brick brick;
            if ( i == 3 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }

        // 15
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 5; ++i) {
            Brick brick;
            if ( i == 5 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 3; ++i) {
            Brick brick;
            if ( i == 3 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }

        // 16
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 3; ++i) {
            Brick brick;
            if ( i == 3 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }

        // 17
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }
        // 18
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 5; ++i) {
            Brick brick;
            if ( i == 5 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }

        // 19
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 2; ++i) {
            Brick brick;
            if ( i == 2 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }

        // 20
        startX += offsetX * DIR_RIGHT * 2;
        for (int i =0; i < 4; ++i) {
            Brick brick;
            if ( i == 4 - 1) {
                brick = new Brick(startX, startY, DIR_LEFT);
            } else {
                brick = new Brick(startX, startY, DIR_RIGHT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_RIGHT;
            startY -= offsetY;
        }

        startX += offsetX * DIR_LEFT * 2;
        for (int i =0; i < 3; ++i) {
            Brick brick;
            if ( i == 3 - 1) {
                brick = new Brick(startX, startY, DIR_RIGHT);
            } else {
                brick = new Brick(startX, startY, DIR_LEFT);
            }
            add(Layer.brick, brick);

            startX += offsetX * DIR_LEFT;
            startY -= offsetY;
        }

        initialized = true;
        return true;
    }

    private void initLayers(int layerCount) {
        layers = new ArrayList<>();
        for (int i = 0; i < layerCount; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public void update() {
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.update();
            }
        }
    }

    public void draw(Canvas canvas) {
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {

            return true;
        }
        return false;
    }

    public void add(Layer layer, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layer.ordinal());
                objects.add(gameObject);
            }
        });
//        Log.d(TAG, "<A> object count = " + objects.size());
    }

    public void remove(GameObject gameObject) {
        remove(gameObject, true);
    }
    public void remove(GameObject gameObject, boolean delayed) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> objects: layers) {
                    boolean removed = objects.remove(gameObject);
                    if (removed) {
//                        if (gameObject instanceof Recyclable) {
//                            ((Recyclable) gameObject).recycle();
//                            recycle(gameObject);
//                        }
                        //Log.d(TAG, "Removed: " + gameObject);
                        break;
                    }
                }
            }
        };
        if (delayed) {
            GameView.view.post(runnable);
        } else {
            runnable.run();
        }
    }
}
