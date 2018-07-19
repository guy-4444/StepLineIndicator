# StepLineIndicator

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/vlad1m1r990/Lemniscate/blob/master/LICENSE)
[![](https://jitpack.io/v/guy-4444/StepLineIndicator.svg)](https://jitpack.io/#guy-4444/StepLineIndicator)
[![API](https://img.shields.io/badge/API-21%2B-green.svg?style=flat)]()

Vertical and horizontal step line indicator.


![device-2018-06-06-144912](https://user-images.githubusercontent.com/35038142/41039463-2bf1ce0a-69a2-11e8-963a-3e0259757565.png)


## Setup
Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency:

```
dependencies {
  implementation 'com.github.guy-4444:StepLineIndicator:1.02.01'
}
```
## Usage

###### StepView Constructor:
```java
        StepLineLayout stepLayout_1 = (StepLineLayout)findViewById(R.id.timeLayout_1);
        stepLayout_1.setStepLines(this, SteplineView.LineOrientation.HORIZONTAL, 5, R.color.colorPrimary, android.R.color.darker_gray);
	
	// or:
	StepLineLayout stepLayout_1 = (StepLineLayout)findViewById(R.id.stepLayout_1);
	stepLayout_1.setStepLines(this, SteplineView.LineOrientation.HORIZONTAL, 7, R.color.colorPrimary, android.R.color.darker_gray, R.color.colorPrimary);

        // or:
        StepLineLayout stepLayout_2 = (StepLineLayout)findViewById(R.id.stepLayout_2);
        stepLayout_2.setStepLines(this, SteplineView.LineOrientation.VERTICAL, 12, Color.argb(123, 0, 0, 255), android.R.color.darker_gray, android.R.color.darker_gray, 3, 20, 10);
	
        // or:
        StepLineLayout stepLayout_3 = (StepLineLayout)findViewById(R.id.stepLayout_3);
        stepLayout_2.setStepLines(this, SteplineView.LineOrientation.VERTICAL, 12, Color.argb(123, 0, 0, 255), android.R.color.darker_gray, android.R.color.darker_gray, 3, 20, 10);
	
        // or:
        StepLineLayout stepLayout_4 = (StepLineLayout)findViewById(R.id.stepLayout_4);
	stepLayout_4.setStepLines(this, SteplineView.LineOrientation.VERTICAL, 60, Color.argb(123, 0, 0, 255), android.R.color.darker_gray, android.R.color.darker_gray, 2, 17, 0);
       
       // or with item click listener:
       StepLineLayout stepLayout_5 = (StepLineLayout)findViewById(R.id.stepLayout_5);
       stepLayout_5.setStepLines(this, StepLineView.LineOrientation.VERTICAL, 40, R.color.colorPrimary, android.R.color.darker_gray, Color.argb(123, 200, 200, 0), 4, 25, 0, new CallBack_StepViewClick() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, position + " Clicked!!", Toast.LENGTH_SHORT).show();
            }
        });

```

###### StepView Constructor parameters:

**Context context**

**LineOrientation** _lineOrientation - SteplineView.LineOrientation.VERTICAL or SteplineView.LineOrientation.HORIZONTAL

**int _numOfItems**

**int _mainColor** - color of completed items

**int _secondColor** - color of uncompleted items

**int _lineColor** - color of lines between items

**int _lineSize** - 0 is without lines

**int _markerRadius** - size of items

**int _linePadding** - 0 is without space between lines to items

###### StepView Control ways:
```java
        stepLayout_1.setUnitCompleted(0);
        stepLayout_1.setUnitInactive(1);
        stepLayout_1.setUnitActive(2);
```
## Credits

Thanks to [vipulasri](https://github.com/vipulasri/Timeline-View)

## License

    Copyright 2018 Guy Isakov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
